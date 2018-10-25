package test

import com.disney.omnicart.persist.HBaseClient

import scala.io.Source

object ParserTest {
  def main(args: Array[String]): Unit = {

    val filePath = "/Users/sidhartharay/Documents/Disney/workspace/omnicart-data-prep/src/main/resources/sample_data/sample_input_data_kafka.txt"

    val hbaseClient = new HBaseClient()
    try {
/*      hbaseClient.initialize("omnicart_features")
      val data = Source.fromFile(filePath)
      for (line <- data.getLines()) {
        val cartIdList = hbaseClient.queryAllRowIds()
        val omnicart = HBaseClient.parse(line)
        if (omnicart.getDestination() == "WDW"
          && (omnicart.getCartTotalAmountList().nonEmpty && omnicart.getCartTotalAmountList()(0) > 0)
          && (omnicart.getCartStartDate() != null && omnicart.getAreaStartDateList().nonEmpty && omnicart.getCartStartDate().getTime <= omnicart.getAreaStartDateList()(0).getTime)
          && (omnicart.getAreaStartDateList().nonEmpty && omnicart.getLastModifiedDateList().nonEmpty && omnicart.getLastModifiedDateList()(0).getTime <= omnicart.getAreaStartDateList()(0).getTime)) {
          if (!cartIdList.contains(omnicart.getCartId())) {
            var omnicartHbaseList = new mutable.ListBuffer[OmnicartHbase]()
            omnicartHbaseList += Converters.convert(omnicart)
            hbaseClient.insert(omnicart.getCartId(), "features", "json", Converters.toJson(omnicartHbaseList))
          } else {
            var omnicartHbaseList = hbaseClient.retrieve(omnicart.getCartId())
            var latestOmnicart = Converters.convert(omnicartHbaseList.sortBy(cart => -cart.eventNum)(Ordering[Int])(0))
            latestOmnicart = updateLatestCart(omnicart, latestOmnicart)
            omnicartHbaseList += Converters.convert(latestOmnicart)
            hbaseClient.insert(omnicart.getCartId(), "features", "json", Converters.toJson(omnicartHbaseList))
          }
        }
      }*/
    } catch {
      case ex:Exception => ex.printStackTrace()
    } finally {
      hbaseClient.close()
    }
  }

}
