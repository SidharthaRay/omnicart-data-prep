
import org.apache.hadoop.hbase.{CellUtil, HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.util.Bytes
//import org.apache.hadoop.hbase.spark.HBaseContext

object HbaseLoadTest {

  def main(args: Array[String]) {


    val config = HBaseConfiguration.create()
    val connection = ConnectionFactory.createConnection(config)

    // Instantiating HTable class// Instantiating HTable class
    val table = connection.getTable(TableName.valueOf(Bytes.toBytes("omnicart_features")))

    // Instantiating the Scan class
    val scan = new Scan()

    // Scanning the required columns
    scan.addColumn(Bytes.toBytes("features"), Bytes.toBytes("json"))

    // Getting the scan result
    val scanner = table.getScanner(scan)

    // Reading values from scan result
    var result = scanner.next

    while (result != null) {
      val cells = result.rawCells()
      var json:String = null
      for(cell <- cells) {
        val col_value = Bytes.toString(CellUtil.cloneValue(cell))
        println(col_value)
      }
      result = scanner.next
    }

    //closing the scanner
    scanner.close()
    table.close()
    connection.close()

  }


}