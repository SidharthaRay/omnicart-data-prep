package test

import java.text.SimpleDateFormat

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row

object SparkTest {

  val filePath = "src/main/resources/sample_data/sampleFromS3.txt"

  def main(args: Array[String]): Unit = {
    
    val sparkSession = 
      SparkSession.builder.master("local[*]").appName("Dataframe Example").getOrCreate()
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", "AKIAJWL3N26OYIYJDNEA")
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", "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")

    val kc_extract_1_20171009RawRDD = sparkSession.sparkContext.textFile("s3n://thirutest/KC_Extract_1_20171009.csv")

    val regisheader:String = kc_extract_1_20171009RawRDD.first()
    val kc_extractRDD = kc_extract_1_20171009RawRDD.filter((line:String) => line != regisheader)
          .map(record =>  record.split("\\|",-1))
          .map(record =>  Row(record(0).toString,
            record(1).toInt,record(2).toLong,
            record(3).toString,
            record(4).toString,
            record(5).toString,
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(record(6)).getTime,record(7).toString,
            record(8).toString,record(9).toString,record(10).toString,record(11).toString,record(12).toString,
            record(13).toString,record(14).toString))

/*
        val RegisSchema = StructType(
          StructField("REGIS_CNTRY_CODE", StringType,  true) ::
            StructField("REGIS_CTY_CODE", IntegerType, true) ::
            StructField("REGIS_ID", LongType, true) ::
            StructField("REGIS_LTY_ID", StringType, true) ::
            StructField("REGIS_CNSM_ID", StringType, true) ::
            StructField("REGIS_DATE", StringType, true) ::
            StructField("REGIS_TIME", TimestampType, true) ::
            StructField("REGIS_CHANNEL", StringType, true) ::
            StructField("REGIS_GENDER", StringType, true) ::
            StructField("REGIS_CITY", StringType, true) ::
            StructField("CHILD_ID", StringType, true) ::
            StructField("CHILD_NB", StringType, true) ::
            StructField("CHILD_GENDER", StringType, true) ::
            StructField("CHILD_DOB", StringType, true) ::
            StructField("CHILD_DECEASED", StringType, true) :: Nil)

        val regisDF = sparkSession.createDataFrame(kc_extractRDD, RegisSchema)
        regisDF.show()
*/

      sparkSession.close()
  }
}
