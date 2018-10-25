package com.disney.omnicart

import com.disney.omnicart.model.{Omnicart, OmnicartHbase}
import com.disney.omnicart.parser.Parser.{parse, updateLatestCart}
import com.disney.omnicart.persist.HBaseClient
import com.disney.omnicart.utils.Converters
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession

import scala.collection.mutable

object PreProcessor {

//  val filePath = "/Users/sidhartharay/Documents/Disney/workspace/omnicart-data-prep/src/main/resources/sample_data/sample_input_data_kafka.txt"
  val filePath = "/Users/sidhartharay/Documents/Disney/workspace/omnicart-data-prep/src/main/resources/sample_data/sample_input_data.txt"
//  val filePath = "/Users/sidhartharay/Documents/Disney/workspace/omnicart-data-prep/src/main/resources/sample_data/standalone_cart3.txt"
//  val filePath = "/Users/sidhartharay/Documents/Disney/workspace/data/omnicart/data_1hr/"

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession
      .builder
      .master("local[*]")
      .appName("Omnicart Data Pipiline")
      .getOrCreate()

    val rootConfig = ConfigFactory.load("application.conf").getConfig("conf")
    val hbaseClient = new HBaseClient()

    try {

      val hbaseConf = rootConfig.getConfig("hbase_client")
      val omnicartData = sparkSession.sparkContext.textFile(filePath)
      omnicartData
        .map(rec => rec.replace("}{", "}\n{"))
        .flatMap(rec => rec.split("\n"))
        .map(record => parse(record))
        .filter (
          omnicart => omnicart.getDestination() == "WDW"
            && (omnicart.getCartTotalAmountList().nonEmpty && omnicart.getCartTotalAmountList()(0) > 0)
            && (omnicart.getCartStartDate() != null && omnicart.getAreaStartDateList().nonEmpty && omnicart.getCartStartDate().getTime <= omnicart.getAreaStartDateList()(0).getTime)
            && (omnicart.getAreaStartDateList().nonEmpty && omnicart.getLastModifiedDateList().nonEmpty && omnicart.getLastModifiedDateList()(0).getTime <= omnicart.getAreaStartDateList()(0).getTime)
        )
        .foreachPartition {
          omnicartPartition =>
            hbaseClient.initialize(hbaseConf.getString("table_name"))

            omnicartPartition.foreach {
              omnicart =>
                if (!hbaseClient.contains(omnicart.getCartId())) {
                  var omnicartHbaseList = new mutable.ListBuffer[OmnicartHbase]()
                  omnicartHbaseList += Converters.convert(omnicart)
                  hbaseClient.insert(omnicart.getCartId(),
                    hbaseConf.getString("column_family"),
                    hbaseConf.getString("column_name"),
                    Converters.toJson(omnicartHbaseList))
                } else {
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
        }
    } catch {

      case ex:Exception => ex.printStackTrace()

    } finally {
//      if(hbaseClient.isActive()) {
//        hbaseClient.close()
//      }
      sparkSession.close()
    }
  }
}
