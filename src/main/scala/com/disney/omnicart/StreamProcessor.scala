package com.disney.omnicart

import com.disney.omnicart.model.{Omnicart, OmnicartHbase}
import com.disney.omnicart.parser.Parser.{parse, updateLatestCart}
import com.disney.omnicart.persist.HBaseClient
import com.disney.omnicart.utils.Converters
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.{Encoders, SparkSession}
import org.apache.spark.sql.streaming.Trigger

import scala.collection.mutable

object StreamProcessor {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession
      .builder
      .master("local[*]")
      .appName("Omnicart Data Pipiline")
      .getOrCreate()

    val rootConfig = ConfigFactory.load("application.conf").getConfig("conf")
    val hbaseConf = rootConfig.getConfig("hbase_client")
    val hbaseClient = new HBaseClient()

    import sparkSession.sqlContext.implicits._

    val omnicartRawDf = sparkSession
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "omnicart_topic")
      .option("startingoffsets", "earliest")
      .load()

/*
    val omnicartRawDf = sparkSession
      .readStream
      .text("/Users/sidhartharay/Documents/Disney/workspace/omnicart-data-prep/src/main/resources/stream_dir")
*/

    val omnicartEncoder = Encoders.kryo[Omnicart]
    val omnicartDs = omnicartRawDf
      .selectExpr("CAST (value AS STRING) as cart")
      .as[String]
      .map(record=> parse(record))(omnicartEncoder)
      .filter (
        omnicart => omnicart.getDestination() == "WDW"
          && (omnicart.getCartTotalAmountList().nonEmpty && omnicart.getCartTotalAmountList()(0) > 0)
          && (omnicart.getCartStartDate() != null && omnicart.getAreaStartDateList().nonEmpty && omnicart.getCartStartDate().getTime <= omnicart.getAreaStartDateList()(0).getTime)
          && (omnicart.getAreaStartDateList().nonEmpty && omnicart.getLastModifiedDateList().nonEmpty && omnicart.getLastModifiedDateList()(0).getTime <= omnicart.getAreaStartDateList()(0).getTime)
      )

    import org.apache.spark.sql.ForeachWriter
    val writer = new ForeachWriter[Omnicart] {
      override def open(partitionId: Long, version: Long) = {
        hbaseClient.initialize(hbaseConf.getString("table_name"))
        true
      }

      override def process(omnicart: Omnicart) = {
        println(omnicart)
        if (!hbaseClient.contains(omnicart.getCartId())) {
          println("New record..")
          var omnicartHbaseList = new mutable.ListBuffer[OmnicartHbase]()
          omnicartHbaseList += Converters.convert(omnicart)
          hbaseClient.insert(omnicart.getCartId(),
            hbaseConf.getString("column_family"),
            hbaseConf.getString("column_name"),
            Converters.toJson(omnicartHbaseList))
        } else {
          println("Old record..")
          var omnicartHbaseList = hbaseClient.retrieve(omnicart.getCartId())
          var latestOmnicart = Converters.convert(omnicartHbaseList.sortBy(cart => -cart.eventNum)(Ordering[Int])(0))
          latestOmnicart = updateLatestCart(omnicart, latestOmnicart)
          omnicartHbaseList += Converters.convert(latestOmnicart)
          for(omnicartHbase <- omnicartHbaseList) {
            omnicartHbase.swid = omnicart.getSwid()
          }
          hbaseClient.insert(omnicart.getCartId(),
            hbaseConf.getString("column_family"),
            hbaseConf.getString("column_name"),
            Converters.toJson(omnicartHbaseList))
        }
    }

      override def close(errorOrNull: Throwable) = {
        hbaseClient.close()
      }
    }

    val query = omnicartDs.writeStream
      .foreach(writer)
      .start()


    query.awaitTermination()



  }
}
