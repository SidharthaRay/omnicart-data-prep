package test

import java.io.Serializable

import net.liftweb.json.Serialization.write
import net.liftweb.json._

import scala.collection.mutable

object JsonTest {
  def main(args: Array[String]): Unit = {
    val p = new OmnicartMock("123abc123", "abc123abc", new Address("line1", "line2"))
    val q = new OmnicartMock("1234abc1234", "abcd123abcd", new Address("1line1", "1line2"))
    val list = new mutable.MutableList[OmnicartMock]()
    list += p
    list += q
    // create a JSON string from the Person, then print it
    implicit val formats = DefaultFormats
    val jsonString = write(list)
    println(jsonString)
    val str =
      """[{"cartId":"123abc123","swid":"abc123abc","address":{"line1":"line1","line2":"line2"}},{"cartId":"1234abc1234","swid":"abcd123abcd","address":{"line1":"1line1","line2":"1line2"}}]"""
    val l2 = parse(str).extract[List[OmnicartMock]]
    println(l2.toString)

  }
}


case class OmnicartMock(cartId:String, swid:String, address:Address) extends Serializable
case class Address(line1:String, line2:String)