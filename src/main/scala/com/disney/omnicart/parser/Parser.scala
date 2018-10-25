package com.disney.omnicart.parser

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import com.disney.omnicart.model.{Omnicart, OmnicartHbase}
import com.disney.omnicart.utils.{Converters, Utility}

import scala.collection.mutable
import scala.util.parsing.json.JSON

@SerialVersionUID(1L)
object Parser extends Serializable {

  /**
    * Adding Omnicart features
    *
    * @param record
    * @return
    */
  def parse(record: String): Omnicart = {
    try {
      val cartJson: Map[String, Any] = JSON.parseFull(record).get.asInstanceOf[Map[String, Any]].get("cart").get.asInstanceOf[Map[String, Any]]

      // Creating Omnicart object with cartId
      var omnicart = new Omnicart()
      omnicart.setCartId(cartJson.get("id").get.asInstanceOf[String])
      omnicart.setEventNum(1)

      // Adding areaStartDate, areaStartDateDistinctCount, areaStartDateMonth and daysTripDuration, daysTripDurationMedian, daysTripDurationDistinctCount
      parseDaysTripDuration(omnicart, cartJson)

      // Adding swid, cartStatus, cartTotalAmount, cartTotalAmountMedian, cartTotalAmountDistinctCount cartCookieId, cartSecondaryGuests, destination
      parseCartDetails(omnicart, cartJson)

      // Adding cartStartDate, daysCreatedAheadOfTripMedian, daysCreatedAheadOfTripDistinctCount and createDatetimeMonth
      parseDaysCreatedAheadOfTrip(omnicart, cartJson)

      // Adding daysModifiedAheadOfTripList, daysModifiedAheadOfTripMedian, daysModifiedAheadOfTripDistinctCount, cartEndDate cartActiveDurationMinutes
      parseDaysModifiedAheadOfTrip(omnicart, cartJson)

      // Adding vacation offer details
      parseVacationOffer(omnicart, cartJson)


      // Adding package details as well as the ticket and room details
      parsePackages(omnicart, cartJson)


      // Adding standalone ticket and room details
      parseStandaloneTicket(omnicart, cartJson)

      // Adding adultCountMax, adultCountMedian, adultCountDistinctCount, childCountMax, childCountMedian and childCountDistinctCount
      parsePartMix(omnicart)

      return omnicart
    }
    catch {
      case ex: Exception => ex.printStackTrace()
        return null
    }
  }


  /**
    * Updating Omnicart features
    *
    * @param newCart
    * @param latestCart
    * @return
    */
  def updateLatestCart(newCart: Omnicart, latestCart: Omnicart): Omnicart = {

    // Updating areaStartDate, areaStartDateDistinctCount, areaStartDateMonth and daysTripDuration, daysTripDurationMedian, daysTripDurationDistinctCount
    updateDaysTripDuration(newCart, latestCart)

    // Updating swid, cartStatus, cartTotalAmount, cartTotalAmountMedian, cartTotalAmountDistinctCount cartCookieId, cartSecondaryGuests, destination
    updateCartDetails(newCart, latestCart)

    // Updating cartStartDate, daysCreatedAheadOfTripMedian, daysCreatedAheadOfTripDistinctCount and createDatetimeMonth
    updateDaysCreatedAheadOfTrip(newCart, latestCart)

    // Updating daysModifiedAheadOfTripList, daysModifiedAheadOfTripMedian, daysModifiedAheadOfTripDistinctCount, cartEndDate cartActiveDurationMinutes
    updateDaysModifiedAheadOfTrip(newCart, latestCart)

    // Updating vacation offer details
    updateVacationOffer(newCart, latestCart)

    // Adding package details as well as the ticket and room details
    updatePackage(newCart, latestCart)

    // Adding package details as well as the ticket and room details
    updateStandaloneTicket(newCart, latestCart)

    // Adding adultCountMax, adultCountMedian, adultCountDistinctCount, childCountMax, childCountMedian and childCountDistinctCount
    updatePartMix(newCart, latestCart)

    return latestCart
  }


  /**
    * Adding swid, cartStatus, cartTotalAmount, cartTotalAmountMedian, cartTotalAmountDistinctCount cartCookieId, cartSecondaryGuests, destination
    *
    * @param omnicart
    * @param cartJson
    * @return
    */
  def parseCartDetails(omnicart: Omnicart, cartJson: Map[String, Any]): Unit = {

    omnicart.setCartStatus(cartJson.get("cartStatus").get.asInstanceOf[String].toUpperCase)
    omnicart.addToCartStatusList(cartJson.get("cartStatus").get.asInstanceOf[String].toUpperCase())

    val swid = cartJson.get("swid").get.asInstanceOf[String]
    if (swid != null) {
      omnicart.setSwid(swid)
      omnicart.addToSwidList(swid)
      omnicart.setCartSwidExists(true)
    }
    omnicart.addToCartTotalAmountList(cartJson.get("cartTotalAmount").get.asInstanceOf[Double])
    omnicart.setCartTotalAmountMean(Utility.mean(omnicart.getCartTotalAmountList()))
    omnicart.setCartTotalAmountMedian(Utility.medianDouble(omnicart.getCartTotalAmountList()))
    omnicart.setCartTotalAmountDistinctCount(omnicart.getCartTotalAmountList.toSet.size)
    if(cartJson.contains("websiteCookieId") && cartJson.get("websiteCookieId").get.isInstanceOf[String]) {
      omnicart.setCartCookieId(cartJson.get("websiteCookieId").get.asInstanceOf[String])
      omnicart.setCartCookieIdExists(true)
      omnicart.addToCartCookieIdExistsList(true)
    } else {
      omnicart.setCartCookieIdExists(false)
      omnicart.addToCartCookieIdExistsList(false)
    }
    omnicart.setCartCookieIdDistinctCount(1)
    if (cartJson.get("secondaryGuests").get.asInstanceOf[List[Double]] != null) {
      omnicart.setCartSecondaryGuests(true)
      omnicart.addToCartSecondaryGuestsList(true)
    } else {
      omnicart.setCartSecondaryGuests(false)
      omnicart.addToCartSecondaryGuestsList(false)
    }

    val destination = cartJson.get("destination").get.asInstanceOf[String]
    if (destination != null) {
      omnicart.setDestination(destination)
      omnicart.addToDestinationList(destination)
    }

  }


  /**
    * Updating swid, cartStatus, cartTotalAmount, cartTotalAmountMedian, cartTotalAmountDistinctCount cartCookieId, cartSecondaryGuests, destination
    *
    * @param newCart
    * @param latestCart
    * @return
    */
  def updateCartDetails(newCart: Omnicart, latestCart: Omnicart): Unit = {

    latestCart.setEventNum(latestCart.getEventNum() + newCart.getEventNum())
    latestCart.addToCartStatusList(newCart.getCartStatus())
    if (latestCart.getCartStatusList().contains("BOOKED"))
      latestCart.setCartStatus("BOOKED")
    else if (latestCart.getCartStatusList().contains("REJECTED"))
      latestCart.setCartStatus("REJECTED")
    else
      latestCart.setCartStatus("ACTIVE")

    if (newCart.getSwid() != null) {
      latestCart.setSwid(newCart.getSwid())
      latestCart.addToSwidList(newCart.getSwid())
      latestCart.setCartSwidExists(true)
    }

    latestCart.addToCartTotalAmountList(newCart.getCartTotalAmountList()(0))
    latestCart.setCartTotalAmountMean(Utility.mean(latestCart.getCartTotalAmountList()))
    latestCart.setCartTotalAmountMedian(Utility.medianDouble(latestCart.getCartTotalAmountList()))
    latestCart.setCartTotalAmountDistinctCount(latestCart.getCartTotalAmountList().toSet.size)
    if (newCart.getCartCookieId() != null)
      latestCart.setCartCookieId(newCart.getCartCookieId())
    if (newCart.getCartCookieIdExistsList().nonEmpty) {
      latestCart.addToCartCookieIdExistsList(newCart.getCartCookieIdExistsList()(0))
      if(latestCart.getCartCookieIdExistsList.contains(true))
        latestCart.setCartCookieIdExists(true)
    }
    latestCart.addToCartSecondaryGuestsList(newCart.getCartSecondaryGuests())
    if(latestCart.getCartSecondaryGuestsList.contains(true)) {
      latestCart.setCartSecondaryGuests(true)
    } else {
      latestCart.setCartSecondaryGuests(false)
    }
    if (newCart.getDestination() != null) {
      latestCart.setDestination(newCart.getDestination())
      latestCart.addToDestinationList(newCart.getDestination())
    }

  }


  /**
    * Adding areaStartDate, areaStartDateDistinctCount, areaStartDateMonth, daysTripDuration, daysTripDurationMedian, daysTripDurationDistinctCount
    *
    * @param omnicart
    * @param cartJson
    * @return
    */
  def parseDaysTripDuration(omnicart: Omnicart, cartJson: Map[String, Any]): Unit = {

    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
    if(cartJson.get("areaStartDate").get.asInstanceOf[String] != null) {
      val areaStartDate: Timestamp = new Timestamp(dateFormat.parse(cartJson.get("areaStartDate").get.asInstanceOf[String]).getTime)

      omnicart.addToAreaStartDateList(areaStartDate)
      omnicart.setAreaStartDateDistinctCount(omnicart.getAreaStartDateList.toSet.size)

      val areaEndDate: Timestamp = new Timestamp(dateFormat.parse(cartJson.get("areaEndDate").get.asInstanceOf[String]).getTime)
      var calendar: Calendar = Calendar.getInstance()
      calendar.setTime(new Date(areaStartDate.getTime))
      val areaStartDateMonth = calendar.get(Calendar.MONTH) + 1
      omnicart.setAreaStartDateMonth(areaStartDateMonth)
      omnicart.addToAreaStartDateMonthList(areaStartDateMonth)

      val daysTripDuration = (areaEndDate.getTime - areaStartDate.getTime) / (1000 * 3600 * 24)
      omnicart.setDaysTripDuration(daysTripDuration)

      omnicart.addToDaysTripDurationList(daysTripDuration)
      omnicart.setDaysTripDurationDistinctCount(omnicart.getDaysTripDurationList.toSet.size)
      omnicart.setDaysTripDurationMedian(Utility.median(omnicart.getDaysTripDurationList))
    }
  }


  /**
    * Updating areaStartDate, areaStartDateDistinctCount, areaStartDateMonth, daysTripDuration, daysTripDurationMedian, daysTripDurationDistinctCount
    *
    * @param newCart
    * @param latestCart
    * @return
    */
  def updateDaysTripDuration(newCart: Omnicart, latestCart: Omnicart): Unit = {

    latestCart.addToAreaStartDateList(newCart.getAreaStartDateList()(0))
    latestCart.setAreaStartDateDistinctCount(latestCart.getAreaStartDateList.toSet.size)
    latestCart.setAreaStartDateMonth(newCart.getAreaStartDateMonth())
    latestCart.addToAreaStartDateMonthList(newCart.getAreaStartDateMonth)
    latestCart.setDaysTripDuration(newCart.getDaysTripDuration)
    latestCart.addToDaysTripDurationList(newCart.getDaysTripDuration)
    latestCart.setDaysTripDurationDistinctCount(latestCart.getDaysTripDurationList.toSet.size)
    latestCart.setDaysTripDurationMedian(Utility.median(latestCart.getDaysTripDurationList))

  }


  /**
    * Adding cartStartDate, daysCreatedAheadOfTripMedian, daysCreatedAheadOfTripDistinctCount and createDatetimeMonth
    *
    * @param omnicart
    * @param cartJson
    * @return Omnicart
    */
  def parseDaysCreatedAheadOfTrip(omnicart: Omnicart, cartJson: Map[String, Any]): Unit = {

    var dateFormatWithTz:String = "yyyy-MM-dd'T'HH:mm:ss-HH:mm"
    if(cartJson.get("createDateTime").get.asInstanceOf[String].endsWith("Z")) {
      dateFormatWithTz = "yyyy-MM-dd'T'HH:mm:ss"
    }
    var cartStartDate = new Timestamp(new SimpleDateFormat(dateFormatWithTz).parse(cartJson.get("createDateTime").get.asInstanceOf[String]).getTime)
    omnicart.setCartStartDate(cartStartDate)
    omnicart.addToCartStartDateList(cartStartDate)

    var cartEndDate = new Timestamp(new SimpleDateFormat(dateFormatWithTz).parse(cartJson.get("lastModifiedDateTime").get.asInstanceOf[String]).getTime)
    val daysCreatedAheadOfTrip = (cartEndDate.getTime - cartStartDate.getTime) / (1000 * 3600 * 24)
    omnicart.addToDaysCreatedAheadOfTripList(daysCreatedAheadOfTrip)
    omnicart.setDaysCreatedAheadOfTripMedian(Utility.median(omnicart.getDaysCreatedAheadOfTripList))
    omnicart.setDaysCreatedAheadOfTripDistinctCount(omnicart.getDaysCreatedAheadOfTripList.toSet.size)

    var calendar: Calendar = Calendar.getInstance()
    calendar = Calendar.getInstance()
    calendar.setTime(new Date(cartStartDate.getTime))
    val createDatetimeMonth = calendar.get(Calendar.MONTH)
    omnicart.setCreateDatetimeMonth(createDatetimeMonth)

  }


  /**
    * Updating cartStartDate, daysCreatedAheadOfTripMedian, daysCreatedAheadOfTripDistinctCount and createDatetimeMonth
    *
    * @param newCart
    * @param latestCart
    */
  def updateDaysCreatedAheadOfTrip(newCart: Omnicart, latestCart: Omnicart): Unit = {

    latestCart.addToCartStartDateList(newCart.getCartStartDate())
    latestCart.setCartStartDate(latestCart.getCartStartDateList().sortWith(_.getTime < _.getTime)(0))
    latestCart.addToDaysCreatedAheadOfTripList(newCart.getDaysCreatedAheadOfTripList()(0))
    latestCart.setDaysCreatedAheadOfTripMedian(Utility.median(latestCart.getDaysCreatedAheadOfTripList))
    latestCart.setDaysCreatedAheadOfTripDistinctCount(latestCart.getDaysCreatedAheadOfTripList.toSet.size)

    var calendar: Calendar = Calendar.getInstance()
    calendar = Calendar.getInstance()
    calendar.setTime(new Date(latestCart.getCartStartDate.getTime))
    val createDatetimeMonth = calendar.get(Calendar.MONTH)
    latestCart.setCreateDatetimeMonth(createDatetimeMonth)

  }


  /**
    * Adding daysModifiedAheadOfTrip, daysModifiedAheadOfTripMedian, daysModifiedAheadOfTripDistinctCount, cartEndDate and cartActiveDurationMinutes
    *
    * @param omnicart
    * @param cartJson
    * @return
    */
  def parseDaysModifiedAheadOfTrip(omnicart: Omnicart, cartJson: Map[String, Any]): Unit = {

    if(cartJson.get("areaStartDate").get.asInstanceOf[String] != null) {
      var dateFormatWithTz = "yyyy-MM-dd'T'HH:mm:ss-HH:mm"
      if (cartJson.get("createDateTime").get.asInstanceOf[String].endsWith("Z")) {
        dateFormatWithTz = "yyyy-MM-dd'T'HH:mm:ss"
      }
      var cartStartDate = new Timestamp(new SimpleDateFormat(dateFormatWithTz).parse(cartJson.get("createDateTime").get.asInstanceOf[String]).getTime)
      var cartEndDate = new Timestamp(new SimpleDateFormat(dateFormatWithTz).parse(cartJson.get("lastModifiedDateTime").get.asInstanceOf[String]).getTime)
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
      val areaStartDate: Timestamp = new Timestamp(dateFormat.parse(cartJson.get("areaStartDate").get.asInstanceOf[String]).getTime)
      omnicart.addToLastModifiedDateList(cartEndDate)

      val daysModifiedAheadOfTrip = (areaStartDate.getTime - cartEndDate.getTime) / (1000 * 3600 * 24)
      omnicart.addToDaysModifiedAheadOfTripList(daysModifiedAheadOfTrip)
      omnicart.setDaysModifiedAheadOfTripMedian(Utility.median(omnicart.getDaysModifiedAheadOfTripList))
      omnicart.setDaysModifiedAheadOfTripDistinctCount(omnicart.getDaysModifiedAheadOfTripList.toSet.size)
      omnicart.setCartEndDate(cartEndDate)

      val cartActiveDurationMinutes = Math.round((cartEndDate.getTime - cartStartDate.getTime) / (1000 * 60))
      omnicart.setCartActiveDurationMinutes(cartActiveDurationMinutes)
      omnicart.addToCartActiveDurationMinutesList(cartActiveDurationMinutes)
    }
  }


  /**
    * Updating daysModifiedAheadOfTrip, daysModifiedAheadOfTripMedian, daysModifiedAheadOfTripDistinctCount, cartEndDate cartActiveDurationMinutes
    *
    * @param newCart
    * @param latestCart
    */
  def updateDaysModifiedAheadOfTrip(newCart: Omnicart, latestCart: Omnicart): Unit = {

    latestCart.addToLastModifiedDateList(newCart.getLastModifiedDateList()(0))
    latestCart.addToDaysModifiedAheadOfTripList(newCart.getDaysModifiedAheadOfTripList()(0))
    latestCart.setDaysModifiedAheadOfTripMedian(Utility.median(newCart.getDaysModifiedAheadOfTripList))
    latestCart.setDaysModifiedAheadOfTripDistinctCount(newCart.getDaysModifiedAheadOfTripList.toSet.size)
    latestCart.setCartEndDate(latestCart.getLastModifiedDateList().sortWith(_.getTime > _.getTime)(0))
    latestCart.addToCartActiveDurationMinutesList(newCart.getCartActiveDurationMinutes())
    latestCart.setCartActiveDurationMinutes(latestCart.getCartActiveDurationMinutesList().max)

  }


  /**
    * Adding vacation offer details
    *
    * @param omnicart
    * @param cartJson
    * @return
    */
  def parseVacationOffer(omnicart: Omnicart, cartJson: Map[String, Any]): Unit = {

    val vacationOffer = cartJson.get("vacationOffer").get.asInstanceOf[Map[String, Any]]

    if (vacationOffer.get("dme").get != null) {
      omnicart.setDme(true)
    }
    if (vacationOffer.get("flight").get != null) {
      omnicart.setFlightExists(true)
    }
    if (vacationOffer.get("groundTransfer").get != null) {
      omnicart.setGroundTransferExists(true)
    }
    if (vacationOffer.get("insurance").get != null) {
      omnicart.setInsuranceExists(true)
    }
    if (vacationOffer.get("personalMagic").get != null) {
      omnicart.setPersonalMagicExists(true)
    }
    if (vacationOffer.get("rentalCar").get != null) {
      omnicart.setRentalCarExists(true)
    }

  }


  /**
    * Updating vacation offer details
    *
    * @param newCart
    * @param latestCart
    */
  def updateVacationOffer(newCart: Omnicart, latestCart: Omnicart): Unit = {

    if (newCart.getDme()) {
      latestCart.setDme(true)
    }
    if (newCart.getFlightExists()) {
      latestCart.setFlightExists(true)
    }
    if (newCart.getGroundTransferExists()) {
      latestCart.setGroundTransferExists(true)
    }
    if (newCart.getInsuranceExists()) {
      latestCart.setInsuranceExists(true)
    }
    if (newCart.getPersonalMagicExists()) {
      latestCart.setPersonalMagicExists(true)
    }
    if (newCart.getRentalCarExists()) {
      latestCart.setRentalCarExists(true)
    }

  }


  /**
    * Adding package details as well as the ticket and room details
    *
    * @param omnicart
    * @param cartJson
    */
  def parsePackages(omnicart: Omnicart, cartJson: Map[String, Any]): Unit = {

    val vacationOffer = cartJson.get("vacationOffer").get.asInstanceOf[Map[String, Any]]
    val packageArray = vacationOffer.get("package").get.asInstanceOf[List[Map[String, Any]]]

    if(packageArray != null) {
      if (packageArray.size != 0) {
        omnicart.setIsPackage(true)
        omnicart.addIsPackageList(true)
      }

      var packageDineFlag = false
      var packageRoomOnlyFlag = false

      for (pkg <- packageArray) {

        if (pkg.contains("dine") && pkg.get("dine").get.isInstanceOf[Map[String, Any]]) {
          packageDineFlag = packageDineFlag || true
        }

        if (pkg.contains("roomOnly") && pkg.get("roomOnly").get.asInstanceOf[Boolean]) {
          packageRoomOnlyFlag = packageRoomOnlyFlag || pkg.get("roomOnly").get.asInstanceOf[Boolean]
        }

        var packageRoomExistsFlag = false
        var roomAdultCount = 0
        var roomChildCount = 0
        var roomChildMinAge: Double = Double.PositiveInfinity
        var roomChildMaxAge: Double = Double.NegativeInfinity
        var roomNumDays = 0L

        if (pkg.contains("room")) {
          val roomArray = pkg.get("room").get.asInstanceOf[List[Map[String, Any]]]
          if (roomArray != null) {
            omnicart.setNumRooms(roomArray.size)
            omnicart.setNumRoomsMin(roomArray.size)
            omnicart.setNumRoomsMax(roomArray.size)
            omnicart.addToNumRoomsList(roomArray.size)
            omnicart.setNumRoomsDistinctCount(1)

            for (room <- roomArray) {
              packageRoomExistsFlag = packageRoomExistsFlag || true
              if (room.contains("resortName") && room.get("resortName").get.isInstanceOf[String]) {
                omnicart.addToResortNameList(room.get("resortName").get.asInstanceOf[String])
              }
              if (room.contains("startDate") && room.get("startDate") != None
                && room.contains("endDate") && room.get("endDate") != None) {
                val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
                val startDate: Timestamp = new Timestamp(dateFormat.parse(room.get("startDate").get.asInstanceOf[String]).getTime)
                val endDate: Timestamp = new Timestamp(dateFormat.parse(room.get("endDate").get.asInstanceOf[String]).getTime)
                roomNumDays += (endDate.getTime - startDate.getTime)
              }

              if (room.contains("partyMix") && room.get("partyMix").get.isInstanceOf[Map[String, Any]]) {
                val partyMix = room.get("partyMix").get.asInstanceOf[Map[String, Any]]
                if (partyMix != null) {
                  if (partyMix.contains("adultCount") && partyMix.get("adultCount").get.isInstanceOf[Double]) {
                    roomAdultCount += partyMix.get("adultCount").get.asInstanceOf[Double].toInt
                  }
                  if (partyMix.contains("childCount") && partyMix.get("childCount").get.isInstanceOf[Double]) {
                    roomChildCount += partyMix.get("childCount").get.asInstanceOf[Double].toInt
                  }
                  if (partyMix.contains("nonAdultAges") && partyMix.get("nonAdultAges").get.isInstanceOf[List[Double]]) {
                    roomChildMinAge = math.min(partyMix.get("nonAdultAges").get.asInstanceOf[List[Double]].min, roomChildMinAge)
                    roomChildMaxAge = math.max(partyMix.get("nonAdultAges").get.asInstanceOf[List[Double]].max, roomChildMaxAge)
                  }

                }
              }
            }
          }
          omnicart.setPackageRoomExists(packageRoomExistsFlag)
          omnicart.addPackageRoomExistsList(packageRoomExistsFlag)
          omnicart.setResortDstcnt(omnicart.getResortNameList().toSet.size)
          omnicart.setRoomAdultCnt(roomAdultCount)
          omnicart.addToRoomAdultCntList(roomAdultCount)
          omnicart.setRoomAdultCntMax(roomAdultCount)
          omnicart.setRoomAdultCntMedian(roomAdultCount)
          omnicart.setRoomAdultCntDstcnt(omnicart.getRoomAdultCntList().size)
          omnicart.setRoomChildCnt(roomChildCount)
          omnicart.addToRoomChildCntList(roomChildCount)
          omnicart.setRoomChildCntMax(roomChildCount)
          omnicart.setRoomChildCntMedian(roomChildCount)
          omnicart.setRoomChildCntDstcnt(omnicart.getRoomChildCntList().size)
          if (roomChildMinAge != Double.PositiveInfinity) {
            omnicart.setRoomChildMinAge(roomChildMinAge)
            omnicart.addToRoomChildMinAgeList(roomChildMinAge)
            omnicart.setRoomChildMinAgeDistinctCount(1)
            omnicart.setRoomChildMinAgeMedian(roomChildMinAge)
            omnicart.setRoomChildMinAgeMin(roomChildMinAge)
          }
          if (roomChildMaxAge != Double.NegativeInfinity) {
            omnicart.setRoomChildMaxAge(roomChildMaxAge)
            omnicart.addToRoomChildMaxAgeList(roomChildMaxAge)
            omnicart.setRoomChildMaxAgeDistinctCount(1)
            omnicart.setRoomChildMaxAgeMedian(roomChildMaxAge)
            omnicart.setRoomChildMaxAgeMax(roomChildMaxAge)
          }
          omnicart.addToRoomNumDaysList((roomNumDays / (1000 * 3600 * 24)).toInt)
          omnicart.setRoomNumDaysMax(omnicart.getRoomNumDaysList()(0))
          omnicart.setRoomNumDaysMean(omnicart.getRoomNumDaysList()(0))
          omnicart.setRoomNumDaysMin(omnicart.getRoomNumDaysList()(0))
        }

        var packageTicketDescriptionList = mutable.ListBuffer[String]()
        if (pkg.contains("ticket") && pkg.get("ticket").get.isInstanceOf[List[Map[String, Any]]]) {
          val ticketArray = pkg.get("ticket").get.asInstanceOf[List[Map[String, Any]]]

          var pkgTicketNumDays: Double = 0
          var pkgTicketParkHopper = false
          var pkgTicketParkHopperPlus = false
          var pkgTicketWaterPark = false

          var pkgTicketAdultCount = 0
          var pkgTicketChildCount = 0
          var pkgTicketChildMinAge: Double = Double.PositiveInfinity
          var pkgTicketChildMaxAge: Double = Double.NegativeInfinity

          if (ticketArray != null) {
            omnicart.setPackageTicket(true)
            for (ticket <- ticketArray) {
              if (ticket.contains("description")) {
                packageTicketDescriptionList += ticket.get("description").get.asInstanceOf[String]
              }
              if (ticket.contains("numberOfDays")) {
                pkgTicketNumDays += ticket.get("numberOfDays").get.asInstanceOf[Double]
              }
              if (ticket.contains("parkHopper")) {
                pkgTicketParkHopper = pkgTicketParkHopper || ticket.get("parkHopper").get.asInstanceOf[Boolean]
              }
              if (ticket.contains("parkHopperPlus")) {
                pkgTicketParkHopperPlus = pkgTicketParkHopperPlus || ticket.get("parkHopperPlus").get.asInstanceOf[Boolean]
              }
              if (ticket.contains("waterPark")) {
                pkgTicketWaterPark = pkgTicketWaterPark || ticket.get("waterPark").get.asInstanceOf[Boolean]
              }
              if (ticket.contains("partyMix")) {
                if (ticket.contains("partyMix")) {
                  val partyMix = ticket.get("partyMix").get.asInstanceOf[Map[String, Any]]
                  if (partyMix.contains("adultCount") && partyMix.get("adultCount").get.isInstanceOf[Double]) {
                    pkgTicketAdultCount += partyMix.get("adultCount").get.asInstanceOf[Double].toInt
                  }
                  if (partyMix.contains("childCount") && partyMix.get("childCount").get.isInstanceOf[Double]) {
                    pkgTicketChildCount += partyMix.get("childCount").get.asInstanceOf[Double].toInt
                  }
                  if (partyMix.contains("nonAdultAges") && partyMix.get("nonAdultAges").get.isInstanceOf[List[Double]]) {
                    pkgTicketChildMinAge = math.min(partyMix.get("nonAdultAges").get.asInstanceOf[List[Double]].min, pkgTicketChildMinAge)
                    pkgTicketChildMaxAge = math.max(partyMix.get("nonAdultAges").get.asInstanceOf[List[Double]].max, pkgTicketChildMaxAge)
                  }
                }
              }
            }
          }

          omnicart.setPackageTicketAdultCnt(pkgTicketAdultCount)
          omnicart.addToPackageTicketAdultCntList(pkgTicketAdultCount)
          omnicart.setPackageTicketAdultCntMax(pkgTicketAdultCount)
          omnicart.setPackageTicketAdultCntMedian(pkgTicketAdultCount)
          omnicart.setPackageTicketAdultCntDstcnt(1)
          omnicart.setPackageTicketChildCnt(pkgTicketChildCount)
          omnicart.addToPackageTicketChildCntList(pkgTicketChildCount)
          omnicart.setPackageTicketChildCntMax(pkgTicketChildCount)
          omnicart.setPackageTicketChildCntMedian(pkgTicketChildCount)
          omnicart.setPackageTicketChildCntDstcnt(1)
          if (pkgTicketChildMinAge != Double.PositiveInfinity) {
            omnicart.setPackageTicketChildMinAge(pkgTicketChildMinAge)
            omnicart.addToPackageTicketChildMinAgeList(pkgTicketChildMinAge)
            omnicart.setPackageTicketChildMinAgeMin(pkgTicketChildMinAge)
          }
          if (pkgTicketChildMaxAge != Double.NegativeInfinity) {
            omnicart.setPackageTicketChildMaxAge(pkgTicketChildMaxAge)
            omnicart.addToPackageTicketChildMaxAgeList(pkgTicketChildMaxAge)
            omnicart.setPackageTicketChildMaxAgeMax(pkgTicketChildMaxAge)
          }

          omnicart.addToPackageTicketDescriptionList(packageTicketDescriptionList)
          omnicart.setPackageTicketDescriptionDstcnt(packageTicketDescriptionList.toSet.size)
          omnicart.setPackageTicketNumDays(pkgTicketNumDays)
          omnicart.addToPackageTicketNumDaysList(pkgTicketNumDays)
          omnicart.setPackageTicketNumDaysMedian(pkgTicketNumDays)
          omnicart.setPackageTicketNumDaysDstcnt(1)
          omnicart.setPackageTicketParkHopper(pkgTicketParkHopper)
          omnicart.addToPackageTicketParkHopperList(pkgTicketParkHopper)
          omnicart.setPackageTicketParkHopperPlus(pkgTicketParkHopperPlus)
          omnicart.addToPackageTicketParkHopperPlusList(pkgTicketParkHopperPlus)
          omnicart.setPackageTicketWaterpark(pkgTicketWaterPark)
          omnicart.addToPackageTicketWaterparkList(pkgTicketWaterPark)
        }

        omnicart.setPackageDineExists(packageDineFlag)
        omnicart.addToPackageDineExistsList(packageDineFlag)
        omnicart.setPackageRoomOnly(packageRoomOnlyFlag)
        omnicart.addToPackageRoomOnlyList(packageRoomOnlyFlag)
      }
    }

    if(omnicart.getNumRoomsList().isEmpty)
      omnicart.addToNumRoomsList(0)
    if(omnicart.getRoomAdultCntList().isEmpty)
      omnicart.addToRoomAdultCntList(0)
    if(omnicart.getRoomChildCntList().isEmpty)
      omnicart.addToRoomChildCntList(0)
    if(omnicart.getRoomChildMinAgeList().isEmpty)
      omnicart.addToRoomChildMinAgeList(0)
    if(omnicart.getRoomChildMaxAgeList().isEmpty)
      omnicart.addToRoomChildMaxAgeList(0)
    if(omnicart.getRoomNumDaysList().isEmpty)
      omnicart.addToRoomNumDaysList(0)

    if(omnicart.getPackageTicketAdultCntList().isEmpty)
      omnicart.addToPackageTicketAdultCntList(0)
    if(omnicart.getPackageTicketChildCntList().isEmpty)
      omnicart.addToPackageTicketChildCntList(0)
    if(omnicart.getPackageTicketChildMinAgeList().isEmpty)
      omnicart.addToPackageTicketChildMinAgeList(0)
    if(omnicart.getPackageTicketChildMaxAgeList().isEmpty)
      omnicart.addToPackageTicketChildMaxAgeList(0)
    if(omnicart.getPackageTicketNumDaysList().isEmpty)
      omnicart.addToPackageTicketNumDaysList(0)

    if(omnicart.getPackageTicketWaterparkList().isEmpty)
      omnicart.addToPackageTicketWaterparkList(false)
    if(omnicart.getPackageTicketParkHopperPlusList().isEmpty)
      omnicart.addToPackageTicketParkHopperPlusList(false)
    if(omnicart.getPackageTicketParkHopperList().isEmpty)
      omnicart.addToPackageTicketParkHopperList(false)
    if(omnicart.getPackageTicketNumDaysList().isEmpty)
      omnicart.addToPackageTicketNumDaysList(0)

    if(omnicart.getPackageTicketParkHopperList().isEmpty)
      omnicart.addToPackageTicketParkHopperList(false)
    if(omnicart.getPackageTicketParkHopperPlusList().isEmpty)
      omnicart.addToPackageTicketParkHopperPlusList(false)
    if(omnicart.getPackageTicketWaterparkList().isEmpty)
      omnicart.addToPackageTicketWaterparkList(false)

  }


  /**
    * Updating package details as well as the ticket and room details
    *
    * @param newCart
    * @param latestCart
    */
  def updatePackage(newCart: Omnicart, latestCart: Omnicart)= {

    if (newCart.getPackageTicket()) {
      latestCart.setPackageTicket(true)
    }

    if (newCart.getIsPackageList().size > 0) {
      latestCart.addIsPackageList(newCart.getIsPackageList()(0))
      if (latestCart.getIsPackageList().contains(true)) {
        latestCart.setIsPackage(true)
      }
    }

    if (newCart.getPackageDineExistsList().size > 0) {
      latestCart.addToPackageDineExistsList(newCart.getPackageDineExistsList()(0))
      if (latestCart.getPackageDineExistsList().contains(true)) {
        latestCart.setPackageDineExists(true)
      }
    }

    if (newCart.getPackageRoomExistsList().size > 0) {
      latestCart.addPackageRoomExistsList(newCart.getPackageRoomExistsList()(0))
      if (latestCart.getPackageRoomExistsList().contains(true)) {
        latestCart.setPackageRoomExists(true)
      }
    }

    if (newCart.getPackageRoomOnlyList().size > 0) {
      latestCart.addToPackageRoomOnlyList(newCart.getPackageRoomOnlyList()(0))
      if (latestCart.getPackageRoomOnlyList().contains(true)) {
        latestCart.setPackageRoomOnly(true)
      }
    }

    if (newCart.getPackageTicketDescriptionList().size > 0) {
      latestCart.addToPackageTicketDescriptionList(newCart.getPackageTicketDescriptionList)
      latestCart.setPackageTicketDescriptionDstcnt(latestCart.getPackageTicketDescriptionList.toSet.size)
    }

    if (newCart.getPackageTicketNumDaysList().size > 0) {
      latestCart.addToPackageTicketNumDaysList(newCart.getPackageTicketNumDaysList()(0))
      latestCart.setPackageTicketNumDays(newCart.getPackageTicketNumDays)
      latestCart.setPackageTicketNumDaysMedian(Utility.medianDouble(latestCart.getPackageTicketNumDaysList()))
      latestCart.setPackageTicketNumDaysDstcnt(latestCart.getPackageTicketNumDaysList().toSet.size)
    }

    if (newCart.getPackageTicketParkHopperList().size > 0) {
      latestCart.addToPackageTicketParkHopperList(newCart.getPackageTicketParkHopperList()(0))
      if (latestCart.getPackageTicketParkHopperList.contains(true)) {
        latestCart.setPackageTicketParkHopper(true)
      }
    }

    if (newCart.getPackageTicketParkHopperPlusList().size > 0) {
      latestCart.addToPackageTicketParkHopperPlusList(newCart.getPackageTicketParkHopperPlusList()(0))
      if (latestCart.getPackageTicketParkHopperPlusList.contains(true)) {
        latestCart.setPackageTicketParkHopperPlus(true)
      }
    }

    if (newCart.getPackageTicketWaterparkList().size > 0) {
      latestCart.addToPackageTicketWaterparkList(newCart.getPackageTicketWaterparkList()(0))
      if (latestCart.getPackageTicketWaterparkList().contains(true)) {
        latestCart.setPackageTicketWaterpark(newCart.getPackageTicketWaterparkList()(0))
      }
    }

    if (newCart.getNumRooms() != 0) {
      latestCart.setNumRooms(newCart.getNumRooms())
      latestCart.addToNumRoomsList(newCart.getNumRooms())
      latestCart.setNumRoomsDistinctCount(latestCart.getNumRoomsList.toSet.size)
      latestCart.setNumRoomsMin(latestCart.getNumRoomsList.min[Int])
      latestCart.setNumRoomsMax(latestCart.getNumRoomsList.max[Int])
    }

    if (newCart.getResortNameList().size != 0) {
      latestCart.addToResortNameList(newCart.getResortNameList())
      latestCart.setResortDstcnt(latestCart.getResortNameList().toSet.size)
    }

    if (newCart.getPackageRoomExists()) {
      latestCart.setPackageRoomExists(true)
    }

    if (newCart.getRoomAdultCntList().size != 0) {
      latestCart.setRoomAdultCnt(newCart.getRoomAdultCntList()(0))
      latestCart.addToRoomAdultCntList(newCart.getRoomAdultCntList()(0))
      latestCart.setRoomAdultCntMax(latestCart.getRoomAdultCntList.max[Int])
      latestCart.setRoomAdultCntMedian(Utility.medianInt(latestCart.getRoomAdultCntList))
      latestCart.setRoomAdultCntDstcnt(latestCart.getRoomAdultCntList().toSet.size)
    }

    if (newCart.getRoomChildCntList().size != 0) {
      latestCart.setRoomChildCnt(newCart.getRoomChildCntList()(0))
      latestCart.addToRoomChildCntList(newCart.getRoomChildCntList()(0))
      latestCart.setRoomChildCntMax(latestCart.getRoomChildCntList().max[Int])
      latestCart.setRoomChildCntMedian(Utility.medianInt(latestCart.getRoomChildCntList()))
      latestCart.setRoomChildCntDstcnt(latestCart.getRoomChildCntList().toSet.size)
    }

    if (newCart.getRoomChildMinAgeList.size != 0) {
      latestCart.setRoomChildMinAge(newCart.getRoomChildMinAgeList()(0))
      latestCart.addToRoomChildMinAgeList(newCart.getRoomChildMinAgeList()(0))
      latestCart.setRoomChildMinAgeDistinctCount(latestCart.getRoomChildMinAgeList().toSet.size)
      latestCart.setRoomChildMinAgeMedian(Utility.medianDouble(latestCart.getRoomChildMinAgeList()))
      latestCart.setRoomChildMinAgeMin(latestCart.getRoomChildMinAgeList().min[Double])
    }
    if (newCart.getRoomChildMaxAgeList().size > 0) {
      latestCart.setRoomChildMaxAge(newCart.getRoomChildMaxAgeList()(0))
      latestCart.addToRoomChildMaxAgeList(newCart.getRoomChildMaxAgeList()(0))
      latestCart.setRoomChildMaxAgeDistinctCount(latestCart.getRoomChildMaxAgeList().toSet.size)
      latestCart.setRoomChildMaxAgeMedian(Utility.medianDouble(latestCart.getRoomChildMaxAgeList()))
      latestCart.setRoomChildMaxAgeMax(latestCart.getRoomChildMaxAgeList().max[Double])
    }
    if (newCart.getRoomNumDaysList().size > 0) {
      latestCart.addToRoomNumDaysList(newCart.getRoomNumDaysList()(0))
      latestCart.setRoomNumDaysMax(latestCart.getRoomNumDaysList().max[Int])
      latestCart.setRoomNumDaysMean(Utility.meanInt(latestCart.getRoomNumDaysList()))
      latestCart.setRoomNumDaysMin(latestCart.getRoomNumDaysList().min[Int])
    }
    if (newCart.getPackageTicketAdultCntList().size > 0) {
      latestCart.setPackageTicketAdultCnt(newCart.getPackageTicketAdultCntList()(0))
      latestCart.addToPackageTicketAdultCntList(newCart.getPackageTicketAdultCntList()(0))
      latestCart.setPackageTicketAdultCntMax(latestCart.getPackageTicketAdultCntList().max[Int])
      latestCart.setPackageTicketAdultCntMedian(Utility.medianInt(latestCart.getPackageTicketAdultCntList()))
      latestCart.setPackageTicketAdultCntDstcnt(latestCart.getPackageTicketAdultCntList.toSet.size)
    }
    if (newCart.getPackageTicketChildCntList().size > 0) {
      latestCart.setPackageTicketChildCnt(newCart.getPackageTicketChildCntList()(0))
      latestCart.addToPackageTicketChildCntList(newCart.getPackageTicketChildCntList()(0))
      latestCart.setPackageTicketChildCntMax(latestCart.getPackageTicketChildCntList().max[Int])
      latestCart.setPackageTicketChildCntMedian(Utility.medianInt(latestCart.getPackageTicketChildCntList()))
      latestCart.setPackageTicketChildCntDstcnt(latestCart.getPackageTicketChildCntList().toSet.size)
    }
    if (newCart.getPackageTicketChildMinAgeList().size > 0) {
      latestCart.setPackageTicketChildMinAge(newCart.getPackageTicketChildMinAgeList()(0))
      latestCart.addToPackageTicketChildMinAgeList(newCart.getPackageTicketChildMinAgeList()(0))
      latestCart.setPackageTicketChildMinAgeMin(latestCart.getPackageTicketChildMinAgeList().min[Double])
    }
    if (newCart.getPackageTicketChildMaxAgeList().size > 0) {
      latestCart.setPackageTicketChildMaxAge(newCart.getPackageTicketChildMaxAgeList()(0))
      latestCart.addToPackageTicketChildMaxAgeList(newCart.getPackageTicketChildMaxAgeList()(0))
      latestCart.setPackageTicketChildMaxAgeMax(latestCart.getPackageTicketChildMaxAgeList().max[Double])
    }
  }


  /**
    * Adding standalone ticket and room details
    *
    * @param omnicart
    * @param cartJson
    */
  def parseStandaloneTicket(omnicart: Omnicart, cartJson: Map[String, Any]): Unit = {

    val saProducts = cartJson.get("standAloneProducts").get.asInstanceOf[Map[String, Any]]

    if (saProducts != null) {
      omnicart.setIsStandalone(true)
      omnicart.addToIsStandaloneList(true)
    }

    if (saProducts != null && saProducts.get("personalMagic").get.isInstanceOf[List[Map[String, Any]]]) {
      val saPersonalMagicList = saProducts.get("personalMagic").get.asInstanceOf[List[Map[String, Any]]]
      omnicart.setStandalonePersonalMagicInd(true)
      omnicart.addToStandalonePersonalMagicIndList(true)
      omnicart.setStandalonePersonalMagicNum(saPersonalMagicList.size)
      omnicart.addToStandalonePersonalMagicNumList(saPersonalMagicList.size)
      omnicart.setStandalonePersonalMagicNumMedian(saPersonalMagicList.size)
    } else {
      omnicart.addToStandalonePersonalMagicIndList(false)
      omnicart.addToStandalonePersonalMagicNumList(0)
      omnicart.addToIsStandaloneList(false)
    }
    omnicart.setStandalonePersonalMagicNumDistinctCount(1)

    var saTicketNumDays = 0
    var saTicketParkHopper = false
    var saTicketParkHopperPlus = false
    var saTicketWaterPark = false

    var saTicketAdultCount = 0
    var saTicketChildCount = 0
    var saTicketChildMinAge:Double = Double.PositiveInfinity
    var saTicketChildMaxAge:Double = Double.NegativeInfinity

    var saTicketDescriptionList = mutable.ListBuffer[String]()

    if (saProducts != null && saProducts.get("ticket").get.isInstanceOf[List[Map[String, Any]]]) {

      val ticketArray = saProducts.get("ticket").get.asInstanceOf[List[Map[String, Any]]]

      omnicart.addToStandaloneTicketNumList(ticketArray.size)
      omnicart.setStandaloneTicketNumMedian(ticketArray.size)
      omnicart.setStandaloneTicketNumDistinctCount(1)

      for(ticket <- ticketArray) {
        if(ticket.contains("description")) {
          saTicketDescriptionList += ticket.get("description").get.asInstanceOf[String]
        }
        if (ticket.contains("numberOfDays") && ticket.get("numberOfDays").get.isInstanceOf[Double]) {
          saTicketNumDays += ticket.get("numberOfDays").get.asInstanceOf[Double].toInt
        }
        if (ticket.contains("parkHopper") && ticket.get("parkHopper").get.isInstanceOf[Boolean]) {
          saTicketParkHopper = saTicketParkHopper || ticket.get("parkHopper").get.asInstanceOf[Boolean]
        }
        if (ticket.contains("parkHopperPlus") && ticket.get("parkHopperPlus").get.isInstanceOf[Boolean]) {
          saTicketParkHopperPlus = saTicketParkHopperPlus || ticket.get("parkHopperPlus").get.asInstanceOf[Boolean]
        }
        if (ticket.contains("waterPark") && ticket.get("waterPark").get.isInstanceOf[Boolean]) {
          saTicketWaterPark = saTicketWaterPark || ticket.get("waterPark").get.asInstanceOf[Boolean]
        }
        if (ticket.contains("partyMix") && ticket.get("partyMix").get.isInstanceOf[Map[String, Any]]) {
          val partyMix = ticket.get("partyMix").get.asInstanceOf[Map[String, Any]]
          if (partyMix != null) {
            if (partyMix.contains("adultCount") && partyMix.get("adultCount").get.isInstanceOf[Double]) {
              saTicketAdultCount += partyMix.get("adultCount").get.asInstanceOf[Double].toInt
            }
            if (partyMix.contains("childCount") && partyMix.get("childCount").get.isInstanceOf[Double]) {
              saTicketChildCount += partyMix.get("childCount").get.asInstanceOf[Double].toInt
            }
            if (partyMix.contains("nonAdultAges") && partyMix.get("nonAdultAges").get.isInstanceOf[List[Double]]) {
              saTicketChildMinAge = math.min(partyMix.get("nonAdultAges").get.asInstanceOf[List[Double]].min, saTicketChildMinAge)
              saTicketChildMaxAge = math.max(partyMix.get("nonAdultAges").get.asInstanceOf[List[Double]].max, saTicketChildMaxAge)
            }
          }

        }
      }
    }

    omnicart.setStandaloneTicketAdultCount(saTicketAdultCount)
    omnicart.addToStandaloneTicketAdultCountList(saTicketAdultCount)
    omnicart.setStandaloneTicketAdultCntMax(saTicketAdultCount)
    omnicart.setStandaloneTicketAdultCountMedian(saTicketAdultCount)
    omnicart.setStandaloneTicketAdultCountDistinctCount(1)

    omnicart.setStandaloneTicketChildCnt(saTicketChildCount)
    omnicart.addToStandaloneTicketChildCntList(saTicketChildCount)
    omnicart.setStandaloneTicketChildCntMax(saTicketChildCount)
    omnicart.setStandaloneTicketChildCntMedian(saTicketChildCount)
    omnicart.setStandaloneTicketChildCntDstcnt(1)

    if(saTicketChildMinAge != Double.PositiveInfinity) {
      omnicart.setStandaloneTicketChildMinAge(saTicketChildMinAge)
      omnicart.addToStandaloneTicketChildMinAgeList(saTicketChildMinAge)
      omnicart.setStandaloneTicketChildMinAgeDistinctCount(1)
      omnicart.setStandaloneTicketChildMinAgeMedian(saTicketChildMinAge)
      omnicart.setStandaloneTicketChildMinAgeMin(saTicketChildMinAge)
    }

    if(saTicketChildMaxAge != Double.NegativeInfinity) {
      omnicart.setStandaloneTicketChildMaxAge(saTicketChildMaxAge)
      omnicart.addToStandaloneTicketChildMaxAgeList(saTicketChildMaxAge)
      omnicart.setStandaloneTicketChildMaxAgeDistinctCount(1)
      omnicart.setStandaloneTicketChildMaxAgeMedian(saTicketChildMaxAge)
      omnicart.setStandaloneTicketChildMaxAgeMax(saTicketChildMaxAge)
    }

    omnicart.addToStandaloneTicketDescriptionList(saTicketDescriptionList)
    omnicart.setStandaloneTicketDescriptionDstcnt(saTicketDescriptionList.toSet.size)
    omnicart.addToStandaloneTicketNumDaysList(saTicketNumDays)
    omnicart.setStandaloneTicketNumDaysDistinctCount(1)
    omnicart.setStandaloneTicketNumDaysMedian(saTicketNumDays)

    omnicart.setStandaloneTicketParkHopper(saTicketParkHopper)
    omnicart.addToStandaloneTicketParkHopperList(saTicketParkHopper)
    omnicart.setStandaloneTicketParkHopperPlus(saTicketParkHopperPlus)
    omnicart.addToStandaloneTicketParkHopperPlusList(saTicketParkHopperPlus)
    omnicart.setStandaloneTicketWaterPark(saTicketWaterPark)
    omnicart.addToStandaloneTicketWaterParkList(saTicketWaterPark)

    if(omnicart.getStandalonePersonalMagicIndList.isEmpty)
      omnicart.addToStandalonePersonalMagicIndList(false)
    if(omnicart.getStandalonePersonalMagicNumList.isEmpty)
      omnicart.addToStandalonePersonalMagicNumList(0)
    if(omnicart.getIsStandaloneList.isEmpty)
      omnicart.addToIsStandaloneList(false)
    if(omnicart.getStandaloneTicketNumList().isEmpty)
      omnicart.addToStandaloneTicketNumList(0)
    if(omnicart.getStandaloneTicketAdultCountList().isEmpty)
      omnicart.addToStandaloneTicketAdultCountList(0)
    if(omnicart.getStandaloneTicketChildCntList().isEmpty)
      omnicart.addToStandaloneTicketChildCntList(0)
    if(omnicart.getStandaloneTicketChildMinAgeList().isEmpty)
      omnicart.addToStandaloneTicketChildMinAgeList(0)
    if(omnicart.getStandaloneTicketChildMaxAgeList().isEmpty)
      omnicart.addToStandaloneTicketChildMaxAgeList(0)
    if(omnicart.getStandaloneTicketNumDaysList().isEmpty)
      omnicart.addToStandaloneTicketNumDaysList(0)
    if(omnicart.getStandaloneTicketParkHopperList().isEmpty)
      omnicart.addToStandaloneTicketParkHopperList(false)
    if(omnicart.getStandaloneTicketParkHopperPlusList().isEmpty)
      omnicart.addToStandaloneTicketParkHopperPlusList(false)
    if(omnicart.getStandaloneTicketWaterParkList().isEmpty)
      omnicart.addToStandaloneTicketWaterParkList(false)

  }


  /**
    * Updating standalone ticket and room details
    *
    * @param newCart
    * @param latestCart
    */
  def updateStandaloneTicket(newCart: Omnicart, latestCart: Omnicart): Unit = {

    if(newCart.getStandalonePersonalMagicIndList().size > 0) {
      latestCart.addToStandalonePersonalMagicIndList(newCart.getStandalonePersonalMagicIndList()(0))
      if(latestCart.getStandalonePersonalMagicIndList.contains(true)) {
        latestCart.setStandalonePersonalMagicInd(true)
      }
    }
    if(newCart.getStandalonePersonalMagicNumList.size > 0) {
      latestCart.setStandalonePersonalMagicNum(newCart.getStandalonePersonalMagicNumList()(0))
      latestCart.addToStandalonePersonalMagicNumList(newCart.getStandalonePersonalMagicNumList()(0))
      latestCart.setStandalonePersonalMagicNumMedian(Utility.medianInt(latestCart.getStandalonePersonalMagicNumList()))
      latestCart.setStandalonePersonalMagicNumDistinctCount(latestCart.getStandalonePersonalMagicNumList.toSet.size)
    }

    if(newCart.getIsStandaloneList().size > 0) {
      latestCart.addToIsStandaloneList(newCart.getIsStandaloneList()(0))
      if(latestCart.getIsStandaloneList.contains(true)) {
        latestCart.setIsStandalone(true)
      }
    }

    if(newCart.getStandaloneTicketNumList().size > 0) {
      latestCart.addToStandaloneTicketNumList(newCart.getStandaloneTicketNumList()(0))
      latestCart.setStandaloneTicketNumMedian(Utility.medianInt(latestCart.getStandaloneTicketNumList()))
      latestCart.setStandaloneTicketNumDistinctCount(latestCart.getStandaloneTicketNumList.toSet.size)
    }

    if(newCart.getStandaloneTicketAdultCountList().nonEmpty) {
      latestCart.setStandaloneTicketAdultCount(newCart.getStandaloneTicketAdultCountList()(0))
      latestCart.addToStandaloneTicketAdultCountList(newCart.getStandaloneTicketAdultCountList()(0))
      latestCart.setStandaloneTicketAdultCntMax(latestCart.getStandaloneTicketAdultCountList().max[Int])
      latestCart.setStandaloneTicketAdultCountMedian(Utility.medianInt(latestCart.getStandaloneTicketAdultCountList))
      latestCart.setStandaloneTicketAdultCountDistinctCount(latestCart.getStandaloneTicketAdultCountList.toSet.size)
    }

    if(newCart.getStandaloneTicketChildCntList().nonEmpty) {
      latestCart.setStandaloneTicketChildCnt(newCart.getStandaloneTicketChildCntList()(0))
      latestCart.addToStandaloneTicketChildCntList(newCart.getStandaloneTicketChildCntList()(0))
      latestCart.setStandaloneTicketChildCntMax(latestCart.getStandaloneTicketChildCntList().max[Int])
      latestCart.setStandaloneTicketChildCntMedian(Utility.medianInt(latestCart.getStandaloneTicketChildCntList()))
      latestCart.setStandaloneTicketChildCntDstcnt(latestCart.getStandaloneTicketChildCntList().toSet.size)
    }

    if(newCart.getStandaloneTicketChildMinAgeList().nonEmpty) {
      latestCart.setStandaloneTicketChildMinAge(newCart.getStandaloneTicketChildMinAgeList()(0))
      latestCart.addToStandaloneTicketChildMinAgeList(newCart.getStandaloneTicketChildMinAgeList()(0))
      latestCart.setStandaloneTicketChildMinAgeDistinctCount(latestCart.getStandaloneTicketChildMinAgeList().toSet.size)
      latestCart.setStandaloneTicketChildMinAgeMedian(Utility.medianDouble(latestCart.getStandaloneTicketChildMinAgeList()))
      latestCart.setStandaloneTicketChildMinAgeMin(latestCart.getStandaloneTicketChildMinAgeList().min[Double])
    }

    if(newCart.getStandaloneTicketChildMaxAgeList().nonEmpty) {
      latestCart.setStandaloneTicketChildMaxAge(newCart.getStandaloneTicketChildMaxAgeList()(0))
      latestCart.addToStandaloneTicketChildMaxAgeList(newCart.getStandaloneTicketChildMaxAgeList()(0))
      latestCart.setStandaloneTicketChildMaxAgeDistinctCount(latestCart.getStandaloneTicketChildMaxAgeList().toSet.size)
      latestCart.setStandaloneTicketChildMaxAgeMedian(Utility.medianDouble(latestCart.getStandaloneTicketChildMaxAgeList()))
      latestCart.setStandaloneTicketChildMaxAgeMax(latestCart.getStandaloneTicketChildMaxAgeList().max[Double])
    }

    if(newCart.getStandaloneTicketDescriptionList().nonEmpty) {
      latestCart.addToStandaloneTicketDescriptionList(newCart.getStandaloneTicketDescriptionList())
      latestCart.setStandaloneTicketDescriptionDstcnt(latestCart.getStandaloneTicketDescriptionList().toSet.size)
    }

    if(newCart.getStandaloneTicketNumDaysList().nonEmpty) {
      latestCart.addToStandaloneTicketNumDaysList(newCart.getStandaloneTicketNumDaysList()(0))
      latestCart.setStandaloneTicketNumDaysDistinctCount(latestCart.getStandaloneTicketNumDaysList.toSet.size)
      latestCart.setStandaloneTicketNumDaysMedian(Utility.medianInt(latestCart.getStandaloneTicketNumDaysList))
    }

    if(newCart.getStandaloneTicketParkHopperList().nonEmpty) {
      latestCart.addToStandaloneTicketParkHopperList(newCart.getStandaloneTicketParkHopperList()(0))
      if(latestCart.getStandaloneTicketParkHopperList.contains(true)) {
        latestCart.setStandaloneTicketParkHopper(newCart.getStandaloneTicketParkHopperList()(0))
      }
    }

    if(newCart.getStandaloneTicketParkHopperPlusList().nonEmpty) {
      latestCart.addToStandaloneTicketParkHopperPlusList(newCart.getStandaloneTicketParkHopperPlusList()(0))
      if(latestCart.getStandaloneTicketParkHopperPlusList.contains(true)) {
        latestCart.setStandaloneTicketParkHopperPlus(true)
      }
    }

    if(newCart.getStandaloneTicketWaterParkList().nonEmpty) {
      latestCart.addToStandaloneTicketWaterParkList(newCart.getStandaloneTicketWaterParkList()(0))
      if(latestCart.getStandaloneTicketWaterParkList.contains(true)) {
        latestCart.setStandaloneTicketWaterPark(true)
      }
    }

  }


  /**
    * Adding adultCountMax, adultCountMedian, adultCountDistinctCount, childCountMax, childCountMedian and childCountDistinctCount
    *
    * @param omnicart
    */
  def parsePartMix (omnicart: Omnicart) = {
    val adultCountMax =
      math.max(
        math.max(omnicart.getPackageTicketAdultCntMax(), omnicart.getRoomAdultCntMax()),
        omnicart.getStandaloneTicketAdultCntMax()
      )
    omnicart.setAdultCountMax(adultCountMax)

    val adultCountMedian =
      math.max(
        math.max(omnicart.getPackageTicketAdultCntMedian(), omnicart.getRoomAdultCntMedian()),
        omnicart.getStandaloneTicketAdultCountMedian
      )
    omnicart.setAdultCountMedian(adultCountMedian)

    val adultCountDistinctCount =
      math.max(
        math.max(omnicart.getPackageTicketAdultCntDstcnt(), omnicart.getRoomAdultCntDstcnt()),
        omnicart.getStandaloneTicketAdultCountDistinctCount()
      )
    omnicart.setAdultCountDistinctCount(adultCountDistinctCount)

    val childCountMax =
      math.max(
        math.max(omnicart.getPackageTicketChildCntMax(), omnicart.getRoomChildCntMax()),
        omnicart.getStandaloneTicketChildCntMax()
      )
    omnicart.setChildCountMax(childCountMax)

    val childCountMedian =
      math.max(
        math.max(omnicart.getPackageTicketChildCntMedian(), omnicart.getRoomChildCntMedian()),
        omnicart.getStandaloneTicketChildCntMedian
      )
    omnicart.setChildCountMedian(childCountMedian)

    val childCountDistinctCount =
      math.max(
        math.max(omnicart.getPackageTicketChildCntDstcnt(), omnicart.getRoomChildCntDstcnt()),
        omnicart.getStandaloneTicketChildCntDstcnt()
      )
    omnicart.setChildCountDistinctCount(childCountDistinctCount)
  }


  /**
    * Updating adultCountMax, adultCountMedian, adultCountDistinctCount, childCountMax, childCountMedian and childCountDistinctCount
    *
    * @param newCart
    * @param latestCart
    */
  def updatePartMix (newCart: Omnicart, latestCart: Omnicart) = {
    val adultCountMax = math.max(newCart.getAdultCountMax(), latestCart.getAdultCountMax())
    latestCart.setAdultCountMax(adultCountMax)

    val adultCountMedian = math.max(newCart.getPackageTicketAdultCntMedian(), latestCart.getRoomAdultCntMedian())
    latestCart.setAdultCountMedian(adultCountMedian)

    val adultCountDistinctCount = math.max(newCart.getPackageTicketAdultCntDstcnt(), latestCart.getRoomAdultCntDstcnt())
    latestCart.setAdultCountDistinctCount(adultCountDistinctCount)

    val childCountMax = math.max(newCart.getPackageTicketChildCntMax(), latestCart.getRoomChildCntMax())
    latestCart.setChildCountMax(childCountMax)

    val childCountMedian = math.max(newCart.getPackageTicketChildCntMedian(), latestCart.getRoomChildCntMedian())
    latestCart.setChildCountMedian(childCountMedian)

    val childCountDistinctCount = math.max(newCart.getPackageTicketChildCntDstcnt(), latestCart.getRoomChildCntDstcnt())
    latestCart.setChildCountDistinctCount(childCountDistinctCount)

  }

}
