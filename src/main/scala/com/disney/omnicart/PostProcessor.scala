package com.disney.omnicart

import com.disney.omnicart.utils.Converters
//import it.nerdammer.spark.hbase._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{StructField, _}

object PostProcessor {
  def main(args: Array[String]): Unit = {
/*    val conf = new SparkConf().setAppName("Omnicart Data Loader").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val omnicartHbaseRdd = sc.hbaseTable[(String, String)]("omnicart_features")
      .select("json")
      .inColumnFamily("features")


    val schema =
      StructType(
        StructField("cartId", StringType, true) ::
          StructField("eventNum", IntegerType, true) ::
          StructField("swid", StringType, true) ::
          StructField("cartStatus", StringType, true) ::
          StructField("cartSwidExists", BooleanType, true) ::
          StructField("cartTotalAmountMean", DoubleType, true) ::
          StructField("cartTotalAmountMedian", DoubleType, true) ::
          StructField("cartTotalAmountDistinctCount", IntegerType, true) ::
          StructField("cartCookieId", StringType, true) ::
          StructField("cartCookieIdDistinctCount", IntegerType, true) ::
          StructField("cartCookieIdExists", BooleanType, true) ::
          StructField("cartSecondaryGuests", BooleanType, true) ::
          StructField("cartSecondaryGuestsDistinctCount", IntegerType, true) ::

          StructField("areaStartDateDistinctCount", IntegerType, true) ::
          StructField("areaStartDateMonth", IntegerType, true) ::
          StructField("daysTripDuration", LongType, true) ::
          StructField("daysTripDurationDistinctCount", IntegerType, true) ::
          StructField("daysTripDurationMedian", LongType, true)::

          StructField("cartStartDate", TimestampType, true)::
          StructField("daysCreatedAheadOfTripMedian", LongType, true)::
          StructField("daysCreatedAheadOfTripDistinctCount", IntegerType, true)::
          StructField("createDatetimeMonth", IntegerType, true)::

          StructField("daysModifiedAheadOfTripMedian", LongType, true)::
          StructField("daysModifiedAheadOfTripDistinctCount", IntegerType, true)::
          StructField("cartEndDate", TimestampType, true)::
          StructField("cartActiveDurationMinutes", LongType, true)::

          StructField("dme", BooleanType, true)::
          StructField("destination", StringType, true)::
          StructField("flightExists", BooleanType, true)::
          StructField("groundTransferExists", BooleanType, true)::
          StructField("insuranceExists", BooleanType, true)::
          StructField("personalMagicExists", BooleanType, true)::
          StructField("rentalCarExists", BooleanType, true)::

          StructField("standalonePersonalMagicInd", BooleanType, true)::
          StructField("standalonePersonalMagicNum", IntegerType, true)::
          StructField("standalonePersonalMagicNumMedian", IntegerType, true)::
          StructField("standalonePersonalMagicNumDistinctCount", IntegerType, true)::

          StructField("packageTicketDescriptionDstcnt", IntegerType, true)::
          StructField("packageDineExists", BooleanType, true)::
          StructField("packageRoomExists", BooleanType, true)::
          StructField("packageRoomOnly", BooleanType, true)::
          StructField("packageTicket", BooleanType, true)::
          StructField("isPackage", BooleanType, true)::
          StructField("packageTicketNumDays", DoubleType, true)::
          StructField("packageTicketNumDaysMedian", DoubleType, true)::
          StructField("packageTicketNumDaysDstcnt", IntegerType, true)::
          StructField("packageTicketParkHopper", BooleanType, true)::
          StructField("packageTicketParkHopperPlus", BooleanType, true)::
          StructField("packageTicketAdultCnt", IntegerType, true)::
          StructField("packageTicketAdultCntMax", IntegerType, true)::
          StructField("packageTicketAdultCntMedian", IntegerType, true)::
          StructField("packageTicketAdultCntDstcnt", IntegerType, true)::
          StructField("packageTicketChildCnt", IntegerType, true)::
          StructField("packageTicketChildCntMax", IntegerType, true)::
          StructField("packageTicketChildCntMedian", IntegerType, true)::
          StructField("packageTicketChildCntDstcnt", IntegerType, true)::

          StructField("packageTicketChildMinAge", DoubleType, true)::
          StructField("packageTicketChildMinAgeMin", DoubleType, true)::

          StructField("packageTicketChildMaxAge", DoubleType, true)::
          StructField("packageTicketChildMaxAgeMax", DoubleType, true)::

          StructField("packageTicketWaterpark", BooleanType, true)::
          StructField("numRooms", IntegerType, true)::
          StructField("numRoomsMin", IntegerType, true)::
          StructField("numRoomsMax", IntegerType, true)::
          StructField("numRoomsDistinctCount", IntegerType, true)::
          StructField("resortDstcnt", IntegerType, true)::
          StructField("roomAdultCnt", IntegerType, true)::
          StructField("roomAdultCntMedian", IntegerType, true)::
          StructField("roomAdultCntMax", IntegerType, true)::
          StructField("roomAdultCntDstcnt",IntegerType, true)::
          StructField("roomChildCnt",IntegerType, true)::
          StructField("roomChildCntMax",IntegerType, true)::
          StructField("roomChildCntDstcnt",IntegerType, true)::
          StructField("roomChildCntMedian",IntegerType, true)::
          StructField("roomChildMinAge",DoubleType, true)::
          StructField("roomChildMinAgeDistinctCount", IntegerType, true)::
          StructField("roomChildMinAgeMin", DoubleType, true)::
          StructField("roomChildMinAgeMedian", DoubleType, true)::
          StructField("roomChildMaxAge", DoubleType, true)::
          StructField("roomChildMaxAgeDistinctCount", IntegerType, true)::
          StructField("roomChildMaxAgeMax", DoubleType, true)::
          StructField("roomChildMaxAgeMedian", DoubleType, true)::
          StructField("roomNumDaysMin", IntegerType, true)::
          StructField("roomNumDaysMean", DoubleType, true)::
          StructField("roomNumDaysMax", IntegerType, true)::

          StructField("isStandalone", BooleanType, true)::
          StructField("standaloneTicketDescriptionDstcnt", IntegerType, true)::
          StructField("standaloneTicketNumMedian", IntegerType, true)::
          StructField("standaloneTicketNumDistinctCount", IntegerType, true)::
          StructField("standaloneTicketNumDaysMedian", IntegerType, true)::
          StructField("standaloneTicketNumDaysDistinctCount", IntegerType, true)::
          StructField("standaloneTicketParkHopper", BooleanType, true)::
          StructField("standaloneTicketParkHopperPlus", BooleanType, true)::
          StructField("standaloneTicketAdultCount", IntegerType, true)::
          StructField("standaloneTicketAdultCntMax", IntegerType, true)::
          StructField("standaloneTicketAdultCountMedian", IntegerType, true)::
          StructField("standaloneTicketAdultCountDistinctCount", IntegerType, true)::
          StructField("standaloneTicketChildCnt", IntegerType, true)::
          StructField("standaloneTicketChildCntMax", IntegerType, true)::
          StructField("standaloneTicketChildCntMedian", IntegerType, true)::
          StructField("standaloneTicketChildCntDstcnt", IntegerType, true)::

          StructField("adultCountMax", IntegerType, true)::
          StructField("adultCountMedian", IntegerType, true)::
          StructField("adultCountDistinctCount", IntegerType, true)::
          StructField("childCountMax", IntegerType, true)::
          StructField("childCountMedian", IntegerType, true)::
          StructField("childCountDistinctCount", IntegerType, true)::

          StructField("standaloneTicketChildMinAge", DoubleType, true)::
          StructField("standaloneTicketChildMinAgeDistinctCount", IntegerType, true)::
          StructField("standaloneTicketChildMinAgeMin", DoubleType, true)::
          StructField("standaloneTicketChildMinAgeMedian", DoubleType, true)::

          StructField("standaloneTicketChildMaxAge", DoubleType, true)::
          StructField("standaloneTicketChildMaxAgeDistinctCount", IntegerType, true)::
          StructField("standaloneTicketChildMaxAgeMax", DoubleType, true)::
          StructField("standaloneTicketChildMaxAgeMedian", DoubleType, true)::

          StructField("standaloneTicketWaterPark", BooleanType, true)::

          Nil
      )

    val omnicartRdd = omnicartHbaseRdd
      .flatMap {
        cart =>
          val omnicartHbaseList = Converters.toOmnicartHbaseList(cart._2)
          val omnicartList = omnicartHbaseList.map {
            omnicartHbase =>
              val omnicart = Converters.convert(omnicartHbase)
              omnicart.setCartId(cart._1)
              omnicart
          }
          omnicartList
      }
      .map(omnicart => Row(
        omnicart.getCartId,
        omnicart.getEventNum,
        omnicart.getSwid,
        omnicart.getCartStatus,
        omnicart.getCartSwidExists,
        omnicart.getCartTotalAmountMean,
        omnicart.getCartTotalAmountMedian,
        omnicart.getCartTotalAmountDistinctCount,
        omnicart.getCartCookieId,
        omnicart.getCartCookieIdDistinctCount,
        omnicart.getCartCookieIdExists,
        omnicart.getCartSecondaryGuests,
        omnicart.getCartSecondaryGuestsDistinctCount,

        omnicart.getAreaStartDateDistinctCount,
        omnicart.getAreaStartDateMonth,
        omnicart.getDaysTripDuration,
        omnicart.getDaysTripDurationDistinctCount,
        omnicart.getDaysTripDurationMedian,

        omnicart.getCartStartDate,
        omnicart.getDaysCreatedAheadOfTripMedian,
        omnicart.getDaysCreatedAheadOfTripDistinctCount,
        omnicart.getCreateDatetimeMonth,

        omnicart.getDaysModifiedAheadOfTripMedian,
        omnicart.getDaysModifiedAheadOfTripDistinctCount,
        omnicart.getCartEndDate,
        omnicart.getCartActiveDurationMinutes,

        omnicart.getDme,
        omnicart.getDestination,
        omnicart.getFlightExists,
        omnicart.getGroundTransferExists,
        omnicart.getInsuranceExists,
        omnicart.getPersonalMagicExists,
        omnicart.getRentalCarExists,

        omnicart.getStandalonePersonalMagicInd,
        omnicart.getStandalonePersonalMagicNum,
        omnicart.getStandalonePersonalMagicNumMedian,
        omnicart.getStandalonePersonalMagicNumDistinctCount,

        omnicart.getPackageTicketDescriptionDstcnt,
        omnicart.getPackageDineExists,
        omnicart.getPackageRoomExists,
        omnicart.getPackageRoomOnly,
        omnicart.getPackageTicket,
        omnicart.getIsPackage,
        omnicart.getPackageTicketNumDays,
        omnicart.getPackageTicketNumDaysMedian,
        omnicart.getPackageTicketNumDaysDstcnt,
        omnicart.getPackageTicketParkHopper,
        omnicart.getPackageTicketParkHopperPlus,
        omnicart.getPackageTicketAdultCnt,
        omnicart.getPackageTicketAdultCntMax,
        omnicart.getPackageTicketAdultCntMedian,
        omnicart.getPackageTicketAdultCntDstcnt,
        omnicart.getPackageTicketChildCnt,
        omnicart.getPackageTicketChildCntMax,
        omnicart.getPackageTicketChildCntMedian,
        omnicart.getPackageTicketChildCntDstcnt,

        omnicart.getPackageTicketChildMinAge,
        omnicart.getPackageTicketChildMinAgeMin,

        omnicart.getPackageTicketChildMaxAge,
        omnicart.getPackageTicketChildMaxAgeMax,

        omnicart.getPackageTicketWaterpark,
        omnicart.getNumRooms,
        omnicart.getNumRoomsMin,
        omnicart.getNumRoomsMax,
        omnicart.getNumRoomsDistinctCount,
        omnicart.getResortDstcnt,
        omnicart.getRoomAdultCnt,
        omnicart.getRoomAdultCntMedian,
        omnicart.getRoomAdultCntMax,
        omnicart.getRoomAdultCntDstcnt,
        omnicart.getRoomChildCnt,
        omnicart.getRoomChildCntMax,
        omnicart.getRoomChildCntDstcnt,
        omnicart.getRoomChildCntMedian,
        omnicart.getRoomChildMinAge,
        omnicart.getRoomChildMinAgeDistinctCount,
        omnicart.getRoomChildMinAgeMin,
        omnicart.getRoomChildMinAgeMedian,
        omnicart.getRoomChildMaxAge,
        omnicart.getRoomChildMaxAgeDistinctCount,
        omnicart.getRoomChildMaxAgeMax,
        omnicart.getRoomChildMaxAgeMedian,
        omnicart.getRoomNumDaysMin,
        omnicart.getRoomNumDaysMean,
        omnicart.getRoomNumDaysMax,

        omnicart.getIsStandalone,
        omnicart.getStandaloneTicketDescriptionDstcnt,
        omnicart.getStandaloneTicketNumMedian,
        omnicart.getStandaloneTicketNumDistinctCount,
        omnicart.getStandaloneTicketNumDaysMedian,
        omnicart.getStandaloneTicketNumDaysDistinctCount,
        omnicart.getStandaloneTicketParkHopper,
        omnicart.getStandaloneTicketParkHopperPlus,
        omnicart.getStandaloneTicketAdultCount,
        omnicart.getStandaloneTicketAdultCntMax,
        omnicart.getStandaloneTicketAdultCountMedian,
        omnicart.getStandaloneTicketAdultCountDistinctCount,
        omnicart.getStandaloneTicketChildCnt,
        omnicart.getStandaloneTicketChildCntMax,
        omnicart.getStandaloneTicketChildCntMedian,
        omnicart.getStandaloneTicketChildCntDstcnt,

        omnicart.getAdultCountMax,
        omnicart.getAdultCountMedian,
        omnicart.getAdultCountDistinctCount,
        omnicart.getChildCountMax,
        omnicart.getChildCountMedian,
        omnicart.getChildCountDistinctCount,

        omnicart.getStandaloneTicketChildMinAge,
        omnicart.getStandaloneTicketChildMinAgeDistinctCount,
        omnicart.getStandaloneTicketChildMinAgeMin,
        omnicart.getStandaloneTicketChildMinAgeMedian,

        omnicart.getStandaloneTicketChildMaxAge,
        omnicart.getStandaloneTicketChildMaxAgeDistinctCount,
        omnicart.getStandaloneTicketChildMaxAgeMax,
        omnicart.getStandaloneTicketChildMaxAgeMedian,

        omnicart.getStandaloneTicketWaterPark
      ))

    sqlContext.createDataFrame(omnicartRdd, schema).show(false)

    sc.stop()*/
  }
}