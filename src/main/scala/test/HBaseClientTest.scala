package test

import java.io.{File, PrintWriter}

import com.disney.omnicart.persist.HBaseClient
import com.disney.omnicart.utils.Converters

object HBaseClientTest {
  def main(args: Array[String]): Unit = {

    val hbaseClient = new HBaseClient()
//    hbaseClient.initialize("t1")
    hbaseClient.initialize("omnicart_features")

//    hbaseClient.insert("2", "cf", "k1", "v2")

//    hbaseClient.select("1")

//    println("\nScan Example:")
//    var scan = hbaseClient.table.getScanner(new Scan())
//    scan.asScala.foreach(result => {
//      printRow(result)
//    })

//    hbaseClient.queryAllRowIds()

//    var cartId = "182744163425-6838629-8286375-2466446"
//    var cartId = "240629936747-5316374-7879793-6310408"
//    var cartId = "240629962853-6667066-8115413-6175437"
//    var cartId = "240631024284-4807393-3261262-9551485"
//    var cartId = "240628238955-7856319-8151125-1113680"
    var cartId = "240631024284-4807393-3261262-9551485"
    val data = hbaseClient.retrieve(cartId)
    val dataJson = Converters.toJson(data)
    val pw = new PrintWriter(new File("src/main/resources/out/" + cartId + ".json"))
    pw.write(dataJson)
    pw.close

//        println(hbaseClient.contains("182744163425-6838629-8286375-2466446"))
//    println(hbaseClient.contains("182744163425-6838629-8286375-246644600"))

    hbaseClient.close()
  }

}
