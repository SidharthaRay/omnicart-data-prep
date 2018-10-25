package test

import java.sql.Timestamp
import java.text.SimpleDateFormat

object ScalaTest {
  def main(args: Array[String]): Unit = {
    val dateFormatWithTz = "yyyy-MM-dd'T'HH:mm:ss"
    val str = "2018-05-01T01:03:31Z"
    val ts = new Timestamp(new SimpleDateFormat(dateFormatWithTz).parse(str).getTime)
    println(ts)
  }
}
