package com.disney.omnicart.model

import java.sql.Timestamp

import scala.collection.mutable

@SerialVersionUID(1L)
class Omnicart extends Serializable {

  private var areaStartDateList: mutable.ListBuffer[Timestamp] = new mutable.ListBuffer[Timestamp]()
  private var areaStartDateDistinctCount: Int = 1
  private var areaStartDateMonth: Int = 0
  private var areaStartDateMonthList: mutable.ListBuffer[Int] = new mutable.ListBuffer[Int]()
  private var daysTripDuration: Long = 0L
  private var daysTripDurationList: mutable.ListBuffer[Long] = new mutable.ListBuffer[Long]()
  private var daysTripDurationDistinctCount: Int = 1
  private var daysTripDurationMedian: Long = 0L

  private var cartId: String = null
  private var eventNum = 0
  private var swid: String = null
  private var swidList = new mutable.ListBuffer[String]()
  private var cartStatus: String = null
  private var cartStatusList = new mutable.ListBuffer[String]()
  private var cartSwidExists: Boolean = false
  private var cartTotalAmountList = new mutable.ListBuffer[Double]()
  private var cartTotalAmountMean: Double = 0.0D
  private var cartTotalAmountMedian: Double = 0.0D
  private var cartTotalAmountDistinctCount = 1
  private var cartCookieId: String = null
  private var cartCookieIdDistinctCount = 1
  private var cartCookieIdExists: Boolean = false
  private var cartCookieIdExistsList = new mutable.ListBuffer[Boolean]()
  private var cartSecondaryGuests: Boolean = false
  private var cartSecondaryGuestsList = new mutable.ListBuffer[Boolean]()
  private var cartSecondaryGuestsDistinctCount: Int = 1

  private var cartStartDate: Timestamp = null
  private var cartStartDateList: mutable.ListBuffer[Timestamp] = new mutable.ListBuffer[Timestamp]()
  private var daysCreatedAheadOfTripList = new mutable.ListBuffer[Long]()
  private var daysCreatedAheadOfTripMedian: Long = 0L
  private var daysCreatedAheadOfTripDistinctCount: Int = 1
  private var createDatetimeMonth: Int = 0

  private var lastModifiedDateList = new mutable.ListBuffer[Timestamp]()
  private var daysModifiedAheadOfTripList = new mutable.ListBuffer[Long]()
  private var daysModifiedAheadOfTripMedian: Long = 0L
  private var daysModifiedAheadOfTripDistinctCount: Int = 1
  private var cartEndDate: Timestamp = null
  private var cartActiveDurationMinutesList = new mutable.ListBuffer[Long]()
  private var cartActiveDurationMinutes: Long = 0L

  private var dme = false
  private var destination: String = null
  private var destinationList = new mutable.ListBuffer[String]()
  private var flightExists = false
  private var groundTransferExists = false
  private var insuranceExists = false
  private var personalMagicExists = false
  private var rentalCarExists = false

  private var standalonePersonalMagicInd = false
  private var standalonePersonalMagicIndList = new mutable.ListBuffer[Boolean]()
  private var standalonePersonalMagicNum: Int = 0
  private var standalonePersonalMagicNumList = new mutable.ListBuffer[Int]()
  private var standalonePersonalMagicNumMedian: Int = 0
  private var standalonePersonalMagicNumDistinctCount: Int = 1

  private var packageTicketDescriptionList = new mutable.ListBuffer[String]()
  private var packageTicketDescriptionDstcnt = 1
  private var packageDineExistsList: mutable.ListBuffer[Boolean] = new mutable.ListBuffer[Boolean]()
  private var packageDineExists: Boolean = false
  private var packageRoomExistsList: mutable.ListBuffer[Boolean] = new mutable.ListBuffer[Boolean]()
  private var packageRoomExists: Boolean = false
  private var packageRoomOnlyList: mutable.ListBuffer[Boolean] = new mutable.ListBuffer[Boolean]()
  private var packageRoomOnly: Boolean = false
  private var packageTicket: Boolean = false
  private var isPackageList: mutable.ListBuffer[Boolean] = new mutable.ListBuffer[Boolean]()
  private var isPackage: Boolean = false
  private var packageTicketNumDaysList: mutable.ListBuffer[Double] = new mutable.ListBuffer[Double]()
  private var packageTicketNumDays: Double = 0
  private var packageTicketNumDaysMedian: Double = 0
  private var packageTicketNumDaysDstcnt: Int = 1
  private var packageTicketParkHopperList: mutable.ListBuffer[Boolean] = new mutable.ListBuffer[Boolean]()
  private var packageTicketParkHopper: Boolean = false
  private var packageTicketParkHopperPlusList: mutable.ListBuffer[Boolean] = new mutable.ListBuffer[Boolean]()
  private var packageTicketParkHopperPlus: Boolean = false
  private var packageTicketAdultCntList: mutable.ListBuffer[Int] = new mutable.ListBuffer[Int]()
  private var packageTicketAdultCnt: Int = 0
  private var packageTicketAdultCntMax: Int = 0
  private var packageTicketAdultCntMedian: Int = 0
  private var packageTicketAdultCntDstcnt: Int = 1
  private var packageTicketChildCntList: mutable.ListBuffer[Int] = new mutable.ListBuffer[Int]()
  private var packageTicketChildCnt: Int = 0
  private var packageTicketChildCntMax: Int = 0
  private var packageTicketChildCntMedian: Int = 0
  private var packageTicketChildCntDstcnt: Int = 1

  private var packageTicketChildMinAgeList: mutable.ListBuffer[Double] = new mutable.ListBuffer[Double]()
  private var packageTicketChildMinAge: Double = 0
  private var packageTicketChildMinAgeMin: Double = 0

  private var packageTicketChildMaxAgeList: mutable.ListBuffer[Double] = new mutable.ListBuffer[Double]()
  private var packageTicketChildMaxAge: Double = 0
  private var packageTicketChildMaxAgeMax: Double = 0

  private var packageTicketWaterparkList: mutable.ListBuffer[Boolean] = new mutable.ListBuffer[Boolean]()
  private var packageTicketWaterpark: Boolean = false
  private var numRoomsList: mutable.ListBuffer[Int] = new mutable.ListBuffer[Int]()
  private var numRooms: Int = 0
  private var numRoomsMin: Int = 0
  private var numRoomsMax: Int = 0
  private var numRoomsDistinctCount: Int = 1
  private var resortNameList: mutable.ListBuffer[String] = new mutable.ListBuffer[String]()
  private var resortDstcnt: Int = 1
  private var roomAdultCntList: mutable.ListBuffer[Int] = new mutable.ListBuffer[Int]()
  private var roomAdultCnt: Int = 0
  private var roomAdultCntMedian: Int = 0
  private var roomAdultCntMax: Int = 0
  private var roomAdultCntDstcnt: Int = 1
  private var roomChildCnt: Int = 0
  private var roomChildCntList: mutable.ListBuffer[Int] = new mutable.ListBuffer[Int]()
  private var roomChildCntMax: Int = 0
  private var roomChildCntDstcnt: Int = 1
  private var roomChildCntMedian: Int = 0
  private var roomChildMinAge: Double = 0
  private var roomChildMinAgeList = new mutable.ListBuffer[Double]()
  private var roomChildMinAgeDistinctCount:Int = 1
  private var roomChildMinAgeMin:Double = 0
  private var roomChildMinAgeMedian:Double = 0
  private var roomChildMaxAge: Double = 0
  private var roomChildMaxAgeList = new mutable.ListBuffer[Double]()
  private var roomChildMaxAgeDistinctCount:Int = 1
  private var roomChildMaxAgeMax:Double = 0
  private var roomChildMaxAgeMedian:Double = 0
  private var roomNumDaysList = new mutable.ListBuffer[Int]()
  private var roomNumDaysMin = 0
  private var roomNumDaysMean = 0.0D
  private var roomNumDaysMax = 0

  private var isStandalone = false
  private var isStandaloneList = new mutable.ListBuffer[Boolean]()
  private var standaloneTicketDescriptionList = new mutable.ListBuffer[String]()
  private var standaloneTicketDescriptionDstcnt = 1
  private var standaloneTicketNumList = new mutable.ListBuffer[Int]()
  private var standaloneTicketNumMedian = 0
  private var standaloneTicketNumDistinctCount = 1
  private var standaloneTicketNumDaysList = new mutable.ListBuffer[Int]()
  private var standaloneTicketNumDaysMedian = 0
  private var standaloneTicketNumDaysDistinctCount = 1
  private var standaloneTicketParkHopper = false
  private var standaloneTicketParkHopperList = new mutable.ListBuffer[Boolean]()
  private var standaloneTicketParkHopperPlus = false
  private var standaloneTicketParkHopperPlusList = new mutable.ListBuffer[Boolean]()
  private var standaloneTicketAdultCountList = new mutable.ListBuffer[Int]()
  private var standaloneTicketAdultCount = 0
  private var standaloneTicketAdultCntMax = 0
  private var standaloneTicketAdultCountMedian = 0
  private var standaloneTicketAdultCountDistinctCount = 1
  private var standaloneTicketChildCntList = new mutable.ListBuffer[Int]()
  private var standaloneTicketChildCnt:Int = 0
  private var standaloneTicketChildCntMax:Int = 0
  private var standaloneTicketChildCntMedian:Int = 0
  private var standaloneTicketChildCntDstcnt:Int = 1

  private var adultCountMax = 0
  private var adultCountMedian = 0
  private var adultCountDistinctCount = 1
  private var childCountMax = 0
  private var childCountMedian = 0
  private var childCountDistinctCount = 1

  private var standaloneTicketChildMinAge:Double = 0
  private var standaloneTicketChildMinAgeList = new mutable.ListBuffer[Double]()
  private var standaloneTicketChildMinAgeDistinctCount:Int = 1
  private var standaloneTicketChildMinAgeMin:Double = 0
  private var standaloneTicketChildMinAgeMedian:Double = 0

  private var standaloneTicketChildMaxAge:Double = 0
  private var standaloneTicketChildMaxAgeList = new mutable.ListBuffer[Double]()
  private var standaloneTicketChildMaxAgeDistinctCount:Int = 1
  private var standaloneTicketChildMaxAgeMax:Double = 0
  private var standaloneTicketChildMaxAgeMedian:Double = 0

  private var standaloneTicketWaterParkList = new mutable.ListBuffer[Boolean]()
  private var standaloneTicketWaterPark:Boolean = false

  def getCartId() = cartId

  def setCartId(cartId: String) = {
    this.cartId = cartId
  }

  def setAreaStartDateDistinctCount(areaStartDateDistinctCount: Int) = {
    this.areaStartDateDistinctCount = areaStartDateDistinctCount
  }

  def getAreaStartDateDistinctCount() = areaStartDateDistinctCount

  def addToAreaStartDateList(areaStartDate: Timestamp): Unit = {
    this.areaStartDateList += areaStartDate
  }

  def setAreaStartDateList(areaStartDateList: mutable.ListBuffer[Timestamp]): Unit = {
    this.areaStartDateList = areaStartDateList
  }

  def getAreaStartDateList() = areaStartDateList

  def setAreaStartDateMonth(areaStartDateMonth: Int) = {
    this.areaStartDateMonth = areaStartDateMonth
  }

  def getAreaStartDateMonth() = areaStartDateMonth

  def addToAreaStartDateMonthList(areaStartDateMonth: Int): Unit = {
    this.areaStartDateMonthList += areaStartDateMonth
  }

  def setAreaStartDateMonthList(areaStartDateMonthList: mutable.ListBuffer[Int]): Unit = {
    this.areaStartDateMonthList = areaStartDateMonthList
  }

  def getAreaStartDateMonthList() = areaStartDateMonthList

  def setDaysTripDuration(daysTripDuration: Long) = {
    this.daysTripDuration = daysTripDuration
  }

  def getDaysTripDuration() = daysTripDuration

  def addToDaysTripDurationList(daysTripDuration: Long): Unit = {
    this.daysTripDurationList += daysTripDuration
  }

  def setDaysTripDurationList(daysTripDurationList: mutable.ListBuffer[Long]): Unit = {
    this.daysTripDurationList = daysTripDurationList
  }

  def getDaysTripDurationList() = daysTripDurationList

  def setDaysTripDurationMedian(daysTripDurationMedian: Long) = {
    this.daysTripDurationMedian = daysTripDurationMedian
  }

  def getDaysTripDurationMedian() = daysTripDurationMedian

  def setDaysTripDurationDistinctCount(daysTripDurationDistinctCount: Int) = {
    this.daysTripDurationDistinctCount = daysTripDurationDistinctCount
  }

  def getDaysTripDurationDistinctCount() = daysTripDurationDistinctCount

  def setEventNum(eventNum: Int) = {
    this.eventNum = eventNum
  }

  def getEventNum() = eventNum

  def setSwid(swid: String) = {
    this.swid = swid
  }

  def getSwid() = swid

  def addToSwidList(swid: String) = {
    this.swidList += swid
  }

  def setSwidList(swidList: mutable.ListBuffer[String]) = {
    this.swidList = swidList
  }

  def getSwidList() = swidList

  def setCartSwidExists(cartSwidExists: Boolean) = {
    this.cartSwidExists = cartSwidExists
  }

  def getCartSwidExists() = cartSwidExists

  def addToCartTotalAmountList(cartTotalAmount: Double): Unit = {
    this.cartTotalAmountList += cartTotalAmount
  }

  def setCartTotalAmountList(cartTotalAmountList: mutable.ListBuffer[Double]): Unit = {
    this.cartTotalAmountList = cartTotalAmountList
  }

  def getCartTotalAmountList() = cartTotalAmountList

  def setCartTotalAmountMean(cartTotalAmountMean: Double) = {
    this.cartTotalAmountMean = cartTotalAmountMean
  }

  def getCartTotalAmountMean() = cartTotalAmountMean

  def setCartTotalAmountMedian(cartTotalAmountMedian: Double) = {
    this.cartTotalAmountMedian = cartTotalAmountMedian
  }

  def getCartTotalAmountMedian() = cartTotalAmountMedian

  def setCartTotalAmountDistinctCount(cartTotalAmountDistinctCount: Int) = {
    this.cartTotalAmountDistinctCount = cartTotalAmountDistinctCount
  }

  def getCartTotalAmountDistinctCount() = cartTotalAmountDistinctCount

  def setCartStatus(cartStatus: String) = {
    this.cartStatus = cartStatus
  }

  def getCartStatus() = cartStatus

  def addToCartStatusList(cartStatus: String): Unit = {
    this.cartStatusList += cartStatus
  }

  def setCartStatusList(cartStatusList: mutable.ListBuffer[String]): Unit = {
    this.cartStatusList = cartStatusList
  }

  def getCartStatusList() = cartStatusList

  def setCartCookieId(cartCookieId: String) = {
    this.cartCookieId = cartCookieId
  }

  def getCartCookieId() = cartCookieId

  def setCartCookieIdDistinctCount(cartCookieIdDistinctCount: Int) = {
    this.cartCookieIdDistinctCount = cartCookieIdDistinctCount
  }

  def getCartCookieIdDistinctCount() = cartCookieIdDistinctCount

  def setCartCookieIdExists(cartCookieIdExists: Boolean) = {
    this.cartCookieIdExists = cartCookieIdExists
  }

  def getCartCookieIdExists() = cartCookieIdExists

  def setCartCookieIdExistsList(cartCookieIdExistsList: mutable.ListBuffer[Boolean]) = {
    this.cartCookieIdExistsList = cartCookieIdExistsList
  }

  def addToCartCookieIdExistsList(cartCookieIdExists: Boolean): Unit = {
    this.cartCookieIdExistsList += cartCookieIdExists
  }

  def getCartCookieIdExistsList() = cartCookieIdExistsList

  def setCartSecondaryGuests(cartSecondaryGuests: Boolean) = {
    this.cartSecondaryGuests = cartSecondaryGuests
  }

  def getCartSecondaryGuests() = cartSecondaryGuests

  def setCartSecondaryGuestsDistinctCount(cartSecondaryGuestsDistinctCount: Int) = {
    this.cartSecondaryGuestsDistinctCount = cartSecondaryGuestsDistinctCount
  }

  def getCartSecondaryGuestsDistinctCount() = cartSecondaryGuestsDistinctCount

  def setCartSecondaryGuestsList(cartSecondaryGuestsList: mutable.ListBuffer[Boolean]) = {
    this.cartSecondaryGuestsList = cartSecondaryGuestsList
  }

  def addToCartSecondaryGuestsList(cartSecondaryGuests: Boolean) = {
    this.cartSecondaryGuestsList += cartSecondaryGuests
  }

  def getCartSecondaryGuestsList() = cartSecondaryGuestsList

  def setCartStartDate(cartStartDate: Timestamp) = {
    this.cartStartDate = cartStartDate
  }

  def getCartStartDate() = cartStartDate

  def setCartEndDate(cartEndDate: Timestamp) = {
    this.cartEndDate = cartEndDate
  }

  def getCartEndDate() = cartEndDate

  def addToCartStartDateList(cartStartDate: Timestamp): Unit = {
    this.cartStartDateList += cartStartDate
  }

  def setCartStartDateList(cartStartDateList: mutable.ListBuffer[Timestamp]): Unit = {
    this.cartStartDateList = cartStartDateList
  }

  def getCartStartDateList() = cartStartDateList

  def addToDaysCreatedAheadOfTripList(daysCreatedAheadOfTrip: Long): Unit = {
    this.daysCreatedAheadOfTripList += daysCreatedAheadOfTrip
  }

  def setDaysCreatedAheadOfTripList(daysCreatedAheadOfTripList: mutable.ListBuffer[Long]): Unit = {
    this.daysCreatedAheadOfTripList = daysCreatedAheadOfTripList
  }

  def getDaysCreatedAheadOfTripList() = daysCreatedAheadOfTripList

  def setDaysCreatedAheadOfTripMedian(daysCreatedAheadOfTripMedian: Long) = {
    this.daysCreatedAheadOfTripMedian = daysCreatedAheadOfTripMedian
  }

  def getDaysCreatedAheadOfTripMedian() = daysCreatedAheadOfTripMedian

  def setDaysCreatedAheadOfTripDistinctCount(daysCreatedAheadOfTripDistinctCount: Int) = {
    this.daysCreatedAheadOfTripDistinctCount = daysCreatedAheadOfTripDistinctCount
  }

  def getDaysCreatedAheadOfTripDistinctCount() = daysCreatedAheadOfTripDistinctCount

  def setCreateDatetimeMonth(createDatetimeMonth: Int) = {
    this.createDatetimeMonth = createDatetimeMonth
  }

  def getCreateDatetimeMonth() = createDatetimeMonth

  def addToLastModifiedDateList(lastModifiedDate: Timestamp): Unit = {
    this.lastModifiedDateList += lastModifiedDate
  }

  def setLastModifiedDateList(lastModifiedDateList: mutable.ListBuffer[Timestamp]): Unit = {
    this.lastModifiedDateList = lastModifiedDateList
  }

  def getLastModifiedDateList() = lastModifiedDateList

  def addToDaysModifiedAheadOfTripList(daysModifiedAheadOfTrip: Long): Unit = {
    this.daysModifiedAheadOfTripList += daysModifiedAheadOfTrip
  }

  def setDaysModifiedAheadOfTripList(daysModifiedAheadOfTripList: mutable.ListBuffer[Long]): Unit = {
    this.daysModifiedAheadOfTripList = daysModifiedAheadOfTripList
  }

  def getDaysModifiedAheadOfTripList() = daysModifiedAheadOfTripList

  def setDaysModifiedAheadOfTripMedian(daysModifiedAheadOfTripMedian: Long) = {
    this.daysModifiedAheadOfTripMedian = daysModifiedAheadOfTripMedian
  }

  def getDaysModifiedAheadOfTripMedian() = daysModifiedAheadOfTripMedian

  def setDaysModifiedAheadOfTripDistinctCount(daysModifiedAheadOfTripDistinctCount: Int) = {
    this.daysModifiedAheadOfTripDistinctCount = daysModifiedAheadOfTripDistinctCount
  }

  def getDaysModifiedAheadOfTripDistinctCount() = daysModifiedAheadOfTripDistinctCount

  def addToCartActiveDurationMinutesList(cartActiveDurationMinutes: Long): Unit = {
    this.cartActiveDurationMinutesList += cartActiveDurationMinutes
  }

  def setCartActiveDurationMinutesList(cartActiveDurationMinutesList: mutable.ListBuffer[Long]): Unit = {
    this.cartActiveDurationMinutesList = cartActiveDurationMinutesList
  }

  def getCartActiveDurationMinutesList() = cartActiveDurationMinutesList

  def setCartActiveDurationMinutes(cartActiveDurationMinutes: Long) = {
    this.cartActiveDurationMinutes = cartActiveDurationMinutes
  }

  def getCartActiveDurationMinutes() = cartActiveDurationMinutes

  def setDme(dme: Boolean) = {
    this.dme = dme
  }

  def getDme() = dme

  //  def addToDmeList(dme: Boolean): Unit = {
  //    this.dmeList += dme
  //  }
  //
  //  def getDmeList() = dmeList

  def setDestination(destination: String) = {
    this.destination = destination
  }

  def getDestination() = destination

  def addToDestinationList(destination: String): Unit = {
    this.destinationList += destination
  }

  def setDestinationList(destinationList: mutable.ListBuffer[String]): Unit = {
    this.destinationList = destinationList
  }

  def getDestinationList() = destinationList


  def setFlightExists(flightExists: Boolean) = {
    this.flightExists = flightExists
  }

  def getFlightExists() = flightExists

  //  def addToFlightExistsList(flightExists: Boolean): Unit = {
  //    this.flightExistsList += flightExists
  //  }
  //
  //  def getFlightExistsList() = flightExistsList

  def setGroundTransferExists(groundTransferExists: Boolean) = {
    this.groundTransferExists = groundTransferExists
  }

  def getGroundTransferExists() = groundTransferExists

  //  def addToGroundTransferExistsList(groundTransferExists: Boolean) : Unit = {
  //    this.groundTransferExistsList += groundTransferExists
  //  }
  //
  //  def getGroundTransferExistsList() = groundTransferExistsList

  def setInsuranceExists(insuranceExists: Boolean) = {
    this.insuranceExists = insuranceExists
  }

  def getInsuranceExists() = insuranceExists

  //  def addToInsuranceExistsList(insuranceExists: Boolean): Unit = {
  //    this.insuranceExistsList += insuranceExists
  //  }
  //
  //  def getInsuranceExistsList() = insuranceExistsList

  def setPersonalMagicExists(personalMagicExists: Boolean) = {
    this.personalMagicExists = personalMagicExists
  }

  def getPersonalMagicExists() = personalMagicExists

  //  def addToPersonalMagicExistsList(personalMagicExists: Boolean): Unit = {
  //    this.personalMagicExistsList += personalMagicExists
  //  }
  //
  //  def getPersonalMagicExistsList() = personalMagicExistsList

  def setRentalCarExists(rentalCarExists: Boolean) = {
    this.rentalCarExists = rentalCarExists
  }

  def getRentalCarExists() = rentalCarExists

  //  def addToRentalCarExistsList(rentalCarExists: Boolean): Unit = {
  //    this.rentalCarExistsList += rentalCarExists
  //  }
  //
  //  def getRentalCarExistsList() = rentalCarExistsList

  def setStandalonePersonalMagicInd(standalonePersonalMagicInd: Boolean) = {
    this.standalonePersonalMagicInd = standalonePersonalMagicInd
  }

  def getStandalonePersonalMagicInd() = standalonePersonalMagicInd

  def addToStandalonePersonalMagicIndList(standalonePersonalMagicInd: Boolean): Unit = {
    this.standalonePersonalMagicIndList += standalonePersonalMagicInd
  }

  def setStandalonePersonalMagicIndList(standalonePersonalMagicIndList: mutable.ListBuffer[Boolean]): Unit = {
    this.standalonePersonalMagicIndList = standalonePersonalMagicIndList
  }

  def getStandalonePersonalMagicIndList() = standalonePersonalMagicIndList

  def setStandalonePersonalMagicNum(standalonePersonalMagicNum: Int) = {
    this.standalonePersonalMagicNum = standalonePersonalMagicNum
  }

  def getStandalonePersonalMagicNum() = standalonePersonalMagicNum

  def addToStandalonePersonalMagicNumList(standalonePersonalMagicNum: Int): Unit = {
    this.standalonePersonalMagicNumList += standalonePersonalMagicNum
  }

  def setStandalonePersonalMagicNumList(standalonePersonalMagicNumList: mutable.ListBuffer[Int]): Unit = {
    this.standalonePersonalMagicNumList = standalonePersonalMagicNumList
  }

  def getStandalonePersonalMagicNumList() = standalonePersonalMagicNumList

  def setStandalonePersonalMagicNumMedian(standalonePersonalMagicNumMedian: Int) = {
    this.standalonePersonalMagicNumMedian = standalonePersonalMagicNumMedian
  }

  def getStandalonePersonalMagicNumMedian() = standalonePersonalMagicNumMedian

  def setStandalonePersonalMagicNumDistinctCount(standalonePersonalMagicNumDistinctCount: Int) = {
    this.standalonePersonalMagicNumDistinctCount = standalonePersonalMagicNumDistinctCount
  }

  def getStandalonePersonalMagicNumDistinctCount() = standalonePersonalMagicNumDistinctCount

  def getPackageTicketDescriptionList(): mutable.ListBuffer[String] = packageTicketDescriptionList

  def addToPackageTicketDescriptionList(packageTicketDescriptionList: mutable.ListBuffer[String]): Unit = {
    this.packageTicketDescriptionList ++= packageTicketDescriptionList
  }

  def setPackageTicketDescriptionList(packageTicketDescriptionList: mutable.ListBuffer[String]): Unit = {
    this.packageTicketDescriptionList = packageTicketDescriptionList
  }

  def getPackageTicketDescriptionDstcnt(): Int = packageTicketDescriptionDstcnt

  def setPackageTicketDescriptionDstcnt(packageTicketDescriptionDstcnt: Int): Unit = {
    this.packageTicketDescriptionDstcnt = packageTicketDescriptionDstcnt
  }

  def getPackageDineExistsList(): mutable.ListBuffer[Boolean] = packageDineExistsList

  def addToPackageDineExistsList(dineStatus: Boolean): Unit = {
    this.packageDineExistsList += dineStatus
  }

  def setPackageDineExistsList(dineStatusList: mutable.ListBuffer[Boolean]): Unit = {
    this.packageDineExistsList = dineStatusList
  }

  def getPackageDineExists(): Boolean = packageDineExists

  def setPackageDineExists(packageDineExists: Boolean): Unit = {
    this.packageDineExists = packageDineExists
  }

  def getPackageRoomExistsList(): mutable.ListBuffer[Boolean] = packageRoomExistsList

  def addPackageRoomExistsList(roomStatus: Boolean): Unit = {
    this.packageRoomExistsList += roomStatus
  }

  def setPackageRoomExistsList(roomStatusList: mutable.ListBuffer[Boolean]): Unit = {
    this.packageRoomExistsList = roomStatusList
  }

  def getPackageRoomExists(): Boolean = packageRoomExists

  def setPackageRoomExists(packageRoomExists: Boolean) = {
    this.packageRoomExists = packageRoomExists
  }


  def getPackageRoomOnlyList(): mutable.ListBuffer[Boolean] = packageRoomOnlyList

  def addToPackageRoomOnlyList(roomOnlyStatus: Boolean): Unit = {
    this.packageRoomOnlyList += roomOnlyStatus
  }

  def setPackageRoomOnlyList(roomOnlyStatusList: mutable.ListBuffer[Boolean]): Unit = {
    this.packageRoomOnlyList = roomOnlyStatusList
  }

  def getPackageRoomOnly(): Boolean = packageRoomOnly

  def setPackageRoomOnly(packageRoomOnly: Boolean): Unit = {
    this.packageRoomOnly = packageRoomOnly
  }

  def getPackageTicket(): Boolean = packageTicket

  def setPackageTicket(packageTicket: Boolean): Unit = {
    this.packageTicket = packageTicket
  }


  def getIsPackageList(): mutable.ListBuffer[Boolean] = isPackageList

  def addIsPackageList(ispackage: Boolean): Unit = {
    this.isPackageList += ispackage
  }

  def setIsPackageList(ispackageList: mutable.ListBuffer[Boolean]): Unit = {
    this.isPackageList = ispackageList
  }

  def getIsPackage(): Boolean = isPackage

  def setIsPackage(isPackage: Boolean) = {
    this.isPackage = isPackage
  }


  def getPackageTicketNumDaysList(): mutable.ListBuffer[Double] = packageTicketNumDaysList

  def addToPackageTicketNumDaysList(packageTicketNumDays: Double): Unit = {
    this.packageTicketNumDaysList += packageTicketNumDays
  }

  def setPackageTicketNumDaysList(packageTicketNumDaysList: mutable.ListBuffer[Double]): Unit = {
    this.packageTicketNumDaysList = packageTicketNumDaysList
  }

  def getPackageTicketNumDays(): Double = packageTicketNumDays

  def setPackageTicketNumDays(packageTicketNumDays: Double) = {
    this.packageTicketNumDays = packageTicketNumDays
  }

  def getPackageTicketNumDaysMedian(): Double = packageTicketNumDaysMedian

  def setPackageTicketNumDaysMedian(packageTicketNumDaysMedian: Double) = {
    this.packageTicketNumDaysMedian = packageTicketNumDaysMedian
  }

  def getPackageTicketNumDaysDstcnt(): Int = packageTicketNumDaysDstcnt

  def setPackageTicketNumDaysDstcnt(packageTicketNumDaysDstcnt: Int) = {
    this.packageTicketNumDaysDstcnt = packageTicketNumDaysDstcnt
  }

  def getPackageTicketParkHopperList(): mutable.ListBuffer[Boolean] = packageTicketParkHopperList

  def addToPackageTicketParkHopperList(packageTicketParkHopper: Boolean): Unit = {
    this.packageTicketParkHopperList += packageTicketParkHopper
  }

  def setPackageTicketParkHopperList(packageTicketParkHopperList: mutable.ListBuffer[Boolean]): Unit = {
    this.packageTicketParkHopperList = packageTicketParkHopperList
  }

  def getPackageTicketParkHopper(): Boolean = packageTicketParkHopper

  def setPackageTicketParkHopper(packageTicketParkHopper: Boolean) = {
    this.packageTicketParkHopper = packageTicketParkHopper
  }

  def getPackageTicketParkHopperPlusList(): mutable.ListBuffer[Boolean] = packageTicketParkHopperPlusList

  def addToPackageTicketParkHopperPlusList(packageTicketParkHopperPlus: Boolean): Unit = {
    this.packageTicketParkHopperPlusList += packageTicketParkHopperPlus
  }

  def setPackageTicketParkHopperPlusList(packageTicketParkHopperPlusList: mutable.ListBuffer[Boolean]): Unit = {
    this.packageTicketParkHopperPlusList = packageTicketParkHopperPlusList
  }

  def getPackageTicketParkHopperPlus(): Boolean = packageTicketParkHopperPlus

  def setPackageTicketParkHopperPlus(packageTicketParkHopperPlus: Boolean) = {
    this.packageTicketParkHopperPlus = packageTicketParkHopperPlus
  }

  def getPackageTicketAdultCntList(): mutable.ListBuffer[Int] = packageTicketAdultCntList

  def addToPackageTicketAdultCntList(packageTicketAdultCnt: Int): Unit = {
    this.packageTicketAdultCntList += packageTicketAdultCnt
  }

  def setPackageTicketAdultCntList(packageTicketAdultCntList: mutable.ListBuffer[Int]): Unit = {
    this.packageTicketAdultCntList = packageTicketAdultCntList
  }

  def getPackageTicketAdultCnt(): Int = packageTicketAdultCnt

  def setPackageTicketAdultCnt(packageTicketAdultCnt: Int) = {
    this.packageTicketAdultCnt = packageTicketAdultCnt
  }

  def getPackageTicketAdultCntMax(): Int = packageTicketAdultCntMax

  def setPackageTicketAdultCntMax(packageTicketAdultCntMax: Int) = {
    this.packageTicketAdultCntMax = packageTicketAdultCntMax
  }

  def getPackageTicketAdultCntMedian(): Int = packageTicketAdultCntMedian

  def setPackageTicketAdultCntMedian(packageTicketAdultCntMedian: Int) = {
    this.packageTicketAdultCntMedian = packageTicketAdultCntMedian
  }

  def getPackageTicketAdultCntDstcnt(): Int = packageTicketAdultCntDstcnt

  def setPackageTicketAdultCntDstcnt(packageTicketAdultCntDstcnt: Int) = {
    this.packageTicketAdultCntDstcnt = packageTicketAdultCntDstcnt
  }

  def getPackageTicketChildCntList(): mutable.ListBuffer[Int] = packageTicketChildCntList

  def addToPackageTicketChildCntList(packageTicketChildCnt: Int): Unit = {
    this.packageTicketChildCntList += packageTicketChildCnt
  }

  def setPackageTicketChildCntList(packageTicketChildCntList: mutable.ListBuffer[Int]): Unit = {
    this.packageTicketChildCntList = packageTicketChildCntList
  }

  def getPackageTicketChildCnt(): Int = packageTicketChildCnt

  def setPackageTicketChildCnt(packageTicketChildCnt: Int): Unit = {
    this.packageTicketChildCnt = packageTicketChildCnt
  }

  def getPackageTicketChildCntMax(): Int = packageTicketChildCntMax

  def setPackageTicketChildCntMax(packageTicketChildCntMax: Int) = {
    this.packageTicketChildCntMax = packageTicketChildCntMax
  }

  def getPackageTicketChildCntMedian(): Int = packageTicketChildCntMedian

  def setPackageTicketChildCntMedian(packageTicketChildCntMedian: Int) = {
    this.packageTicketChildCntMedian = packageTicketChildCntMedian
  }

  def getPackageTicketChildCntDstcnt(): Int = packageTicketChildCntDstcnt

  def setPackageTicketChildCntDstcnt(packageTicketChildCntDstcnt: Int) = {
    this.packageTicketChildCntDstcnt = packageTicketChildCntDstcnt
  }

  def getPackageTicketChildMinAgeList(): mutable.ListBuffer[Double] = packageTicketChildMinAgeList

  def addToPackageTicketChildMinAgeList(packageTicketChildMinAge: Double): Unit = {
    this.packageTicketChildMinAgeList += packageTicketChildMinAge
  }

  def setPackageTicketChildMinAgeList(packageTicketChildMinAgeList: mutable.ListBuffer[Double]): Unit = {
    this.packageTicketChildMinAgeList = packageTicketChildMinAgeList
  }

  def getPackageTicketChildMinAge(): Double = packageTicketChildMinAge

  def setPackageTicketChildMinAge(packageTicketChildMinAge: Double) = {
    this.packageTicketChildMinAge = packageTicketChildMinAge
  }

  def getPackageTicketChildMinAgeMin(): Double = packageTicketChildMinAgeMin

  def setPackageTicketChildMinAgeMin(packageTicketChildMinAgeMin: Double) = {
    this.packageTicketChildMinAgeMin = packageTicketChildMinAgeMin
  }

  def getPackageTicketChildMaxAgeList(): mutable.ListBuffer[Double] = packageTicketChildMaxAgeList

  def addToPackageTicketChildMaxAgeList(packageTicketChildMaxAge: Double) = {
    this.packageTicketChildMaxAgeList += packageTicketChildMaxAge
  }

  def setPackageTicketChildMaxAgeList(packageTicketChildMaxAgeList: mutable.ListBuffer[Double]) = {
    this.packageTicketChildMaxAgeList = packageTicketChildMaxAgeList
  }

  def getPackageTicketChildMaxAge() = packageTicketChildMaxAge

  def setPackageTicketChildMaxAge(packageTicketChildMaxAge: Double) = {
    this.packageTicketChildMaxAge = packageTicketChildMaxAge
  }

  def getPackageTicketChildMaxAgeMax() = packageTicketChildMaxAgeMax

  def setPackageTicketChildMaxAgeMax(packageTicketChildMaxAgeMax: Double) = {
    this.packageTicketChildMaxAgeMax = packageTicketChildMaxAgeMax
  }

  def getPackageTicketWaterparkList() = packageTicketWaterparkList

  def addToPackageTicketWaterparkList(packageTicketWaterpark: Boolean): Unit = {
    this.packageTicketWaterparkList += packageTicketWaterpark
  }

  def setPackageTicketWaterparkList(packageTicketWaterparkList: mutable.ListBuffer[Boolean]): Unit = {
    this.packageTicketWaterparkList = packageTicketWaterparkList
  }

  def getPackageTicketWaterpark() = packageTicketWaterpark

  def setPackageTicketWaterpark(packageTicketWaterpark: Boolean) = {
    this.packageTicketWaterpark = packageTicketWaterpark
  }

  def getNumRoomsList() = numRoomsList

  def addToNumRoomsList(numRooms: Int): Unit = {
    this.numRoomsList += numRooms
  }

  def setNumRoomsList(numRoomsList: mutable.ListBuffer[Int]): Unit = {
    this.numRoomsList = numRoomsList
  }

  def getNumRooms() = numRooms

  def setNumRooms(numRooms: Int) = {
    this.numRooms = numRooms
  }

  def getNumRoomsMin() = numRoomsMin

  def setNumRoomsMin(numRoomsMin: Int) = {
    this.numRoomsMin = numRoomsMin
  }

  def getNumRoomsMax() = numRoomsMax

  def setNumRoomsMax(numRoomsMax: Int) = {
    this.numRoomsMax = numRoomsMax
  }

  def getNumRoomsDistinctCount() = numRoomsDistinctCount

  def setNumRoomsDistinctCount(numRoomsDistinctCount: Int): Unit = {
    this.numRoomsDistinctCount = numRoomsDistinctCount
  }

  def getResortNameList() = resortNameList

  def addToResortNameList(resortName: String) = {
    this.resortNameList += resortName
  }

  def addToResortNameList(resortNameList: mutable.ListBuffer[String]) = {
    this.resortNameList ++= resortNameList
  }

  def setResortNameList(resortNameList: mutable.ListBuffer[String]) = {
    this.resortNameList = resortNameList
  }

  def getResortDstcnt() = resortDstcnt

  def setResortDstcnt(resortDstcnt: Int) = {
    this.resortDstcnt = resortDstcnt
  }

  def getRoomAdultCntList(): mutable.ListBuffer[Int] = roomAdultCntList

  def addToRoomAdultCntList(roomAdultCnt: Int) = {
    this.roomAdultCntList += roomAdultCnt
  }

  def setRoomAdultCntList(roomAdultCntList: mutable.ListBuffer[Int]) = {
    this.roomAdultCntList = roomAdultCntList
  }

  def getRoomAdultCnt() = roomAdultCnt

  def setRoomAdultCnt(roomAdultCnt: Int) = {
    this.roomAdultCnt = roomAdultCnt
  }


  def getRoomAdultCntMedian(): Int = roomAdultCntMedian

  def setRoomAdultCntMedian(roomAdultCntMedian: Int) = {
    this.roomAdultCntMedian = roomAdultCntMedian
  }

  def getRoomAdultCntMax(): Int = roomAdultCntMax

  def setRoomAdultCntMax(roomAdultCntMax: Int) = {
    this.roomAdultCntMax = roomAdultCntMax
  }

  def getRoomAdultCntDstcnt(): Int = roomAdultCntDstcnt

  def setRoomAdultCntDstcnt(roomAdultCntDstcnt: Int) = {
    this.roomAdultCntDstcnt = roomAdultCntDstcnt
  }

  def getRoomChildCnt(): Int = roomChildCnt

  def setRoomChildCnt(roomChildCnt: Int): Unit = {
    this.roomChildCnt = roomChildCnt
  }

  def getRoomChildCntList(): mutable.ListBuffer[Int] = roomChildCntList

  def addToRoomChildCntList(roomChildCnt: Int) = {
    this.roomChildCntList += roomChildCnt
  }

  def setRoomChildCntList(roomChildCntList: mutable.ListBuffer[Int]) = {
    this.roomChildCntList = roomChildCntList
  }

  def getRoomChildCntDstcnt() = roomChildCntDstcnt

  def setRoomChildCntDstcnt(roomChildCntDstcnt: Int) = {
    this.roomChildCntDstcnt = roomChildCntDstcnt
  }

  def getRoomChildCntMedian() = roomChildCntMedian

  def setRoomChildCntMedian(roomChildCntMedian: Int) = {
    this.roomChildCntMedian = roomChildCntMedian
  }

  def getRoomChildCntMax() = roomChildCntMax

  def setRoomChildCntMax(roomChildCntMax: Int) = {
    this.roomChildCntMax = roomChildCntMax
  }

  def getRoomChildMinAge() = roomChildMinAge

  def setRoomChildMinAge(roomChildMinAge: Double) = {
    this.roomChildMinAge = roomChildMinAge
  }

  def getRoomChildMinAgeList() = roomChildMinAgeList

  def setRoomChildMinAgeList(roomChildMinAgeList: mutable.ListBuffer[Double]) = {
    this.roomChildMinAgeList = roomChildMinAgeList
  }

  def addToRoomChildMinAgeList(roomChildMinAge: Double) = {
    this.roomChildMinAgeList += roomChildMinAge
  }

  def getRoomChildMinAgeDistinctCount() = roomChildMinAgeDistinctCount

  def setRoomChildMinAgeDistinctCount(roomChildMinAgeDistinctCount: Int) = {
    this.roomChildMinAgeDistinctCount = roomChildMinAgeDistinctCount
  }

  def getRoomChildMinAgeMin() = roomChildMinAgeMin

  def setRoomChildMinAgeMin(roomChildMinAgeMin: Double) = {
    this.roomChildMinAgeMin = roomChildMinAgeMin
  }

  def getRoomChildMinAgeMedian() = roomChildMinAgeMedian

  def setRoomChildMinAgeMedian(roomChildMinAgeMedian: Double) = {
    this.roomChildMinAgeMedian = roomChildMinAgeMedian
  }

  def getRoomChildMaxAge() = roomChildMaxAge

  def setRoomChildMaxAge(roomChildMaxAge: Double) = {
    this.roomChildMaxAge = roomChildMaxAge
  }

  def getRoomChildMaxAgeList() = roomChildMaxAgeList

  def addToRoomChildMaxAgeList(roomChildMaxAge: Double) = {
    this.roomChildMaxAgeList += roomChildMaxAge
  }

  def setRoomChildMaxAgeList(roomChildMaxAgeList: mutable.ListBuffer[Double]) = {
    this.roomChildMaxAgeList = roomChildMaxAgeList
  }

  def getRoomChildMaxAgeDistinctCount() = roomChildMaxAgeDistinctCount

  def setRoomChildMaxAgeDistinctCount(roomChildMaxAgeDistinctCount: Int) = {
    this.roomChildMaxAgeDistinctCount = roomChildMaxAgeDistinctCount
  }

  def getRoomChildMaxAgeMax() = roomChildMaxAgeMax

  def setRoomChildMaxAgeMax(roomChildMaxAgeMax: Double) = {
    this.roomChildMaxAgeMax = roomChildMaxAgeMax
  }

  def getRoomChildMaxAgeMedian() = roomChildMaxAgeMedian

  def setRoomChildMaxAgeMedian(roomChildMaxAgeMedian: Double) = {
    this.roomChildMaxAgeMedian = roomChildMaxAgeMedian
  }

  def getRoomNumDaysList() = roomNumDaysList

  def addToRoomNumDaysList(roomNumDays: Int) = {
    this.roomNumDaysList += roomNumDays
  }

  def setRoomNumDaysList(roomNumDaysList: mutable.ListBuffer[Int]) = {
    this.roomNumDaysList = roomNumDaysList
  }

  def getRoomNumDaysMin() = roomNumDaysMin

  def setRoomNumDaysMin(roomNumDaysMin: Int) = {
    this.roomNumDaysMin = roomNumDaysMin
  }

  def getRoomNumDaysMean() = roomNumDaysMean

  def setRoomNumDaysMean(roomNumDaysMean: Double) = {
    this.roomNumDaysMean = roomNumDaysMean
  }

  def getRoomNumDaysMax() = roomNumDaysMax

  def setRoomNumDaysMax(roomNumDaysMax: Int) = {
    this.roomNumDaysMax = roomNumDaysMax
  }

  def getIsStandalone() = isStandalone

  def setIsStandalone(isStandalone: Boolean) = {
    this.isStandalone = isStandalone
  }

  def getIsStandaloneList() = isStandaloneList

  def addToIsStandaloneList(isStandalone: Boolean): Unit = {
    this.isStandaloneList += isStandalone
  }

  def setIsStandaloneList(isStandaloneList: mutable.ListBuffer[Boolean]): Unit = {
    this.isStandaloneList = isStandaloneList
  }

  def getStandaloneTicketDescriptionList() = standaloneTicketDescriptionList

  def addToStandaloneTicketDescriptionList(standaloneTicketDescriptionList: mutable.ListBuffer[String]): Unit = {
    this.standaloneTicketDescriptionList ++= standaloneTicketDescriptionList
  }

  def setStandaloneTicketDescriptionList(standaloneTicketDescriptionList: mutable.ListBuffer[String]): Unit = {
    this.standaloneTicketDescriptionList = standaloneTicketDescriptionList
  }

  def getStandaloneTicketDescriptionDstcnt() = standaloneTicketDescriptionDstcnt

  def setStandaloneTicketDescriptionDstcnt(standaloneTicketDescriptionDstcnt: Int): Unit = {
    this.standaloneTicketDescriptionDstcnt = standaloneTicketDescriptionDstcnt
  }

  def getStandaloneTicketNumList() = standaloneTicketNumList

  def addToStandaloneTicketNumList(standaloneTicketNum: Int): Unit = {
    this.standaloneTicketNumList += standaloneTicketNum
  }

  def setStandaloneTicketNumList(standaloneTicketNumList: mutable.ListBuffer[Int]): Unit = {
    this.standaloneTicketNumList = standaloneTicketNumList
  }

  def getStandaloneTicketNumMedian() = standaloneTicketNumMedian

  def setStandaloneTicketNumMedian(standaloneTicketNumMedian: Int): Unit = {
    this.standaloneTicketNumMedian = standaloneTicketNumMedian
  }

  def getStandaloneTicketNumDistinctCount() = standaloneTicketNumDistinctCount

  def setStandaloneTicketNumDistinctCount(standaloneTicketNumDistinctCount: Int): Unit = {
    this.standaloneTicketNumDistinctCount = standaloneTicketNumDistinctCount
  }

  def getStandaloneTicketNumDaysList() = standaloneTicketNumDaysList

  def addToStandaloneTicketNumDaysList(standaloneTicketNumDays: Int): Unit = {
    this.standaloneTicketNumDaysList += standaloneTicketNumDays
  }

  def setStandaloneTicketNumDaysList(standaloneTicketNumDaysList: mutable.ListBuffer[Int]): Unit = {
    this.standaloneTicketNumDaysList = standaloneTicketNumDaysList
  }

  //  private var standaloneTicketNumDaysMedian = 0
//  private var standaloneTicketNumDaysDistinctCount = 0
//  private var standaloneTicketParkHopper = false
//  private var standaloneTicketParkHopperList = new mutable.ListBuffer[Boolean]()
//  private var standaloneTicketParkHopperPlus = false
//  private var standaloneTicketParkHopperPlusList = new mutable.ListBuffer[Boolean]()
//  private var standaloneTicketAdultCountList = new mutable.ListBuffer[Int]()
//  private var standaloneTicketAdultCount = 0
//  private var standaloneTicketAdultCntMax = 0
//  private var standaloneTicketAdultCountMedian = 0
//  private var standaloneTicketAdultCountDistinctCount = 0

  def getStandaloneTicketNumDaysMedian()=standaloneTicketNumDaysMedian

  def setStandaloneTicketNumDaysMedian(standaloneTicketNumDaysMedian: Int)={
    this.standaloneTicketNumDaysMedian = standaloneTicketNumDaysMedian
  }

  def getStandaloneTicketNumDaysDistinctCount()=standaloneTicketNumDaysDistinctCount

  def setStandaloneTicketNumDaysDistinctCount (standaloneTicketNumDaysDistinctCount: Int)={
    this.standaloneTicketNumDaysDistinctCount = standaloneTicketNumDaysDistinctCount
  }

  def getStandaloneTicketParkHopper()= standaloneTicketParkHopper

  def setStandaloneTicketParkHopper(standaloneTicketParkHopper: Boolean)={
    this.standaloneTicketParkHopper= standaloneTicketParkHopper
  }

  def getStandaloneTicketParkHopperList()=standaloneTicketParkHopperList

  def addToStandaloneTicketParkHopperList(standaloneTicketParkHopper: Boolean)={
    this.standaloneTicketParkHopperList+= standaloneTicketParkHopper
  }

  def setStandaloneTicketParkHopperList(standaloneTicketParkHopperList: mutable.ListBuffer[Boolean])={
    this.standaloneTicketParkHopperList = standaloneTicketParkHopperList
  }

  def getStandaloneTicketParkHopperPlus()=standaloneTicketParkHopperPlus

  def setStandaloneTicketParkHopperPlus(standaloneTicketParkHopperPlus: Boolean)={
    this.standaloneTicketParkHopperPlus=standaloneTicketParkHopperPlus
  }

  def getStandaloneTicketParkHopperPlusList()=standaloneTicketParkHopperPlusList

  def addToStandaloneTicketParkHopperPlusList(standaloneTicketParkHopperPlus: Boolean)={
    this.standaloneTicketParkHopperPlusList+=standaloneTicketParkHopperPlus
  }

  def setStandaloneTicketParkHopperPlusList(standaloneTicketParkHopperPlusList: mutable.ListBuffer[Boolean])={
    this.standaloneTicketParkHopperPlusList = standaloneTicketParkHopperPlusList
  }

  def getStandaloneTicketAdultCount()=standaloneTicketAdultCount

  def setStandaloneTicketAdultCount(standaloneTicketAdultCount :Int)={
    this.standaloneTicketAdultCount = standaloneTicketAdultCount
  }

  def getStandaloneTicketAdultCountList()=standaloneTicketAdultCountList

  def addToStandaloneTicketAdultCountList(standaloneTicketAdultCount :Int)= {
    this.standaloneTicketAdultCountList += standaloneTicketAdultCount
  }

  def setStandaloneTicketAdultCountList(standaloneTicketAdultCount :mutable.ListBuffer[Int])= {
    this.standaloneTicketAdultCountList = standaloneTicketAdultCountList
  }

  def getStandaloneTicketAdultCntMax()=standaloneTicketAdultCntMax

  def setStandaloneTicketAdultCntMax(standaloneTicketAdultCntMax: Int)={
    this.standaloneTicketAdultCntMax = standaloneTicketAdultCntMax
  }

  def getStandaloneTicketAdultCountMedian()=standaloneTicketAdultCountMedian

  def setStandaloneTicketAdultCountMedian(standaloneTicketAdultCountMedian: Int)={
    this.standaloneTicketAdultCountMedian = standaloneTicketAdultCountMedian
  }

  def getStandaloneTicketAdultCountDistinctCount()=standaloneTicketAdultCountDistinctCount

  def setStandaloneTicketAdultCountDistinctCount(standaloneTicketAdultCountDistinctCount: Int)={
    this.standaloneTicketAdultCountDistinctCount = standaloneTicketAdultCountDistinctCount
  }

  def getStandaloneTicketChildCntList()=standaloneTicketChildCntList

  def addToStandaloneTicketChildCntList(standaloneTicketChildCnt:Int)= {
    this.standaloneTicketChildCntList+=standaloneTicketChildCnt
  }

  def setStandaloneTicketChildCntList(standaloneTicketChildCnt: mutable.ListBuffer[Int])={
    this.standaloneTicketChildCntList = standaloneTicketChildCntList
  }

  def getStandaloneTicketChildCnt():Int=standaloneTicketChildCnt

  def setStandaloneTicketChildCnt(standaloneTicketChildCnt :Int)={
    this.standaloneTicketChildCnt=standaloneTicketChildCnt
  }

  def getStandaloneTicketChildCntMax()=standaloneTicketChildCntMax

  def setStandaloneTicketChildCntMax(standaloneTicketChildCntMax: Int)={
    this.standaloneTicketChildCntMax=standaloneTicketChildCntMax
  }

  def getStandaloneTicketChildCntMedian()=standaloneTicketChildCntMedian

  def setStandaloneTicketChildCntMedian(standaloneTicketChildCntMedian :Int)={
    this.standaloneTicketChildCntMedian=standaloneTicketChildCntMedian
  }

  def getStandaloneTicketChildCntDstcnt()= standaloneTicketChildCntDstcnt

  def setStandaloneTicketChildCntDstcnt(standaloneTicketChildCntDstcnt: Int)={
    this.standaloneTicketChildCntDstcnt = standaloneTicketChildCntDstcnt
  }

  def getAdultCountMax()= adultCountMax

  def setAdultCountMax(adultCountMax: Int)= {
    this.adultCountMax = adultCountMax
  }

  def getAdultCountMedian()= adultCountMedian

  def setAdultCountMedian(adultCountMedian: Int)= {
    this.adultCountMedian = adultCountMedian
  }

  def getAdultCountDistinctCount()= adultCountDistinctCount

  def setAdultCountDistinctCount(adultCountDistinctCount: Int)= {
    this.adultCountDistinctCount = adultCountDistinctCount
  }

  def getChildCountMax()= childCountMax

  def setChildCountMax(childCountMax: Int)= {
    this.childCountMax = childCountMax
  }

  def getChildCountMedian()= childCountMedian

  def setChildCountMedian(childCountMedian: Int)= {
    this.childCountMedian = childCountMedian
  }

  def getChildCountDistinctCount()= childCountDistinctCount

  def setChildCountDistinctCount(childCountDistinctCount: Int)= {
    this.childCountDistinctCount = childCountDistinctCount
  }

  def getStandaloneTicketChildMinAge()=standaloneTicketChildMinAge

  def setStandaloneTicketChildMinAge(standaloneTicketChildMinAge: Double)={
    this.standaloneTicketChildMinAge = standaloneTicketChildMinAge
  }

  def getStandaloneTicketChildMinAgeList()= standaloneTicketChildMinAgeList

  def addToStandaloneTicketChildMinAgeList(standaloneTicketChildMinAge: Double): Unit = {
    this.standaloneTicketChildMinAgeList += standaloneTicketChildMinAge
  }

  def getStandaloneTicketChildMinAgeMin()= standaloneTicketChildMinAgeMin

  def setStandaloneTicketChildMinAgeMin(standaloneTicketChildMinAgeMin: Double)= {
    this.standaloneTicketChildMinAgeMin = standaloneTicketChildMinAgeMin
  }

  def getStandaloneTicketChildMinAgeDistinctCount()= standaloneTicketChildMinAgeDistinctCount

  def setStandaloneTicketChildMinAgeDistinctCount(standaloneTicketChildMinAgeDistinctCount: Int)={
    this.standaloneTicketChildMinAgeDistinctCount = standaloneTicketChildMinAgeDistinctCount
  }

  def getStandaloneTicketChildMinAgeMedian()= standaloneTicketChildMinAgeMedian

  def setStandaloneTicketChildMinAgeMedian(standaloneTicketChildMinAgeMedian: Double)= {
    this.standaloneTicketChildMinAgeMedian = standaloneTicketChildMinAgeMedian
  }

  def getStandaloneTicketChildMaxAge()= standaloneTicketChildMaxAge

  def setStandaloneTicketChildMaxAge (standaloneTicketChildMaxAge: Double): Unit = {
    this.standaloneTicketChildMaxAge = standaloneTicketChildMaxAge
  }

  def getStandaloneTicketChildMaxAgeList()= standaloneTicketChildMaxAgeList

  def addToStandaloneTicketChildMaxAgeList (standaloneTicketChildMaxAge: Double): Unit={
    this.standaloneTicketChildMaxAgeList += standaloneTicketChildMaxAge
  }

  def setStandaloneTicketChildMaxAgeList (standaloneTicketChildMaxAgeList: mutable.ListBuffer[Double]): Unit = {
    this.standaloneTicketChildMaxAgeList = standaloneTicketChildMaxAgeList
  }

  def getStandaloneTicketChildMaxAgeMax()= standaloneTicketChildMaxAgeMax

  def setStandaloneTicketChildMaxAgeMax (standaloneTicketChildMaxAgeMax: Double): Unit = {
    this.standaloneTicketChildMaxAgeMax = standaloneTicketChildMaxAgeMax
  }

  def getStandaloneTicketChildMaxAgeMedian()= standaloneTicketChildMaxAgeMedian

  def setStandaloneTicketChildMaxAgeMedian (standaloneTicketChildMaxAgeMedian: Double): Unit = {
    this.standaloneTicketChildMaxAgeMedian = standaloneTicketChildMaxAgeMedian
  }

  def getStandaloneTicketChildMaxAgeDistinctCount()= standaloneTicketChildMaxAgeDistinctCount

  def setStandaloneTicketChildMaxAgeDistinctCount (standaloneTicketChildMaxAgeDistinctCount: Int): Unit = {
    this.standaloneTicketChildMaxAgeDistinctCount = standaloneTicketChildMaxAgeDistinctCount
  }

  def getStandaloneTicketWaterPark()=standaloneTicketWaterPark

  def setStandaloneTicketWaterPark(standaloneTicketWaterPark: Boolean)={
    this.standaloneTicketWaterPark=standaloneTicketWaterPark
  }

  def getStandaloneTicketWaterParkList()=standaloneTicketWaterParkList

  def addToStandaloneTicketWaterParkList(standaloneTicketWaterPark: Boolean)={
    this.standaloneTicketWaterParkList+=standaloneTicketWaterPark
  }

  def setStandaloneTicketWaterParkList(standaloneTicketWaterParkList: mutable.ListBuffer[Boolean])={
    this.standaloneTicketWaterParkList = standaloneTicketWaterParkList
  }

  override def toString: String = "Omnicart[" +
    cartId +
    "]"
}
