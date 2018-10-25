package com.disney.omnicart.persist

import com.disney.omnicart.model.OmnicartHbase
import com.disney.omnicart.utils.Converters
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{CellUtil, HBaseConfiguration, TableName}

import scala.collection.mutable
import scala.collection.JavaConversions._

@SerialVersionUID(1L)
class HBaseClient extends Serializable {

  var connection:Connection = null
  var table:Table = null

  def initialize(tableName: String): Unit = {
    val conf = HBaseConfiguration.create()
    connection = ConnectionFactory.createConnection(conf)
    table = connection.getTable(TableName.valueOf(Bytes.toBytes(tableName)))
  }

  def insert(rowId: String, colFamily: String, colName: String, colValue: String): Unit = {
    var put = new Put(Bytes.toBytes(rowId))
    put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(colName), Bytes.toBytes(colValue))
    table.put(put)
  }


  def queryAllRowIds(): mutable.ListBuffer[String] = {
    val scan = new Scan()
    val scanner = table.getScanner(scan)
    var rowIds = mutable.ListBuffer[String]()
    for (row <- scanner) {
      rowIds += Bytes.toString(row.getRow)
    }
    return rowIds
  }


  // Query table with rowId
  def contains(rowId: String): Boolean = {
    var get = new Get(Bytes.toBytes(rowId))
    return table.exists(get)
  }


  // Query table with rowId
  def getAllRows(): Unit = {
    var scan = new Scan()
    scan.setCaching(500)
    scan.setCacheBlocks(false)
//    scan.set
  }

  // Retrieve record through rowId
  def retrieve(rowId: String): mutable.ListBuffer[OmnicartHbase] = {
    var get = new Get(Bytes.toBytes(rowId))
    var result = table.get(get)
    val cells = result.rawCells()
    var json:String = null
    for(cell <- cells){
      val col_name = Bytes.toString(CellUtil.cloneQualifier(cell))
      val col_value = Bytes.toString(CellUtil.cloneValue(cell))
      json = col_value
    }
    return Converters.toOmnicartHbaseList(json)
  }


  def isActive(): Boolean = {
    if(connection != null)
      return !connection.isClosed
    else
      return false
  }


  def close(): Unit = {
    table.close()
    connection.close()
  }

}
