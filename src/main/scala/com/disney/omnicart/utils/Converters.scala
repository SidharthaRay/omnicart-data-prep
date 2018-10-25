package com.disney.omnicart.utils

import com.disney.omnicart.model.{Omnicart, OmnicartHbase}
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import net.liftweb.json.parse

import scala.collection.mutable

object Converters {

  /**
    * Converting Omnicart object to OmnicartHbase object
    *
    * @param omnicart
    * @return
    */
  def convert(omnicart: Omnicart): OmnicartHbase = {
    return OmnicartHbase(
      omnicart.getAreaStartDateList().toList,
      omnicart.getAreaStartDateDistinctCount(),
      omnicart.getAreaStartDateMonth(),
      omnicart.getAreaStartDateMonthList().toList,
      omnicart.getDaysTripDuration(),
      omnicart.getDaysTripDurationList().toList,
      omnicart.getDaysTripDurationDistinctCount(),
      omnicart.getDaysTripDurationMedian(),

      omnicart.getEventNum(),
      omnicart.getSwid(),
      omnicart.getSwidList().toList,
      omnicart.getCartStatus(),
      omnicart.getCartStatusList().toList,
      omnicart.getCartSwidExists(),
      omnicart.getCartTotalAmountList().toList,
      omnicart.getCartTotalAmountMean(),
      omnicart.getCartTotalAmountMedian(),
      omnicart.getCartTotalAmountDistinctCount(),
      omnicart.getCartCookieId(),
      omnicart.getCartCookieIdExistsList().toList,
      omnicart.getCartCookieIdDistinctCount(),
      omnicart.getCartCookieIdExists(),
      omnicart.getCartSecondaryGuests(),
      omnicart.getCartSecondaryGuestsList.toList,
      omnicart.getCartSecondaryGuestsDistinctCount,

      omnicart.getCartStartDate(),
      omnicart.getCartStartDateList().toList,
      omnicart.getDaysCreatedAheadOfTripList().toList,
      omnicart.getDaysCreatedAheadOfTripMedian(),
      omnicart.getDaysCreatedAheadOfTripDistinctCount(),
      omnicart.getCreateDatetimeMonth(),

      omnicart.getLastModifiedDateList().toList,
      omnicart.getDaysModifiedAheadOfTripList().toList,
      omnicart.getDaysModifiedAheadOfTripMedian(),
      omnicart.getDaysModifiedAheadOfTripDistinctCount(),
      omnicart.getCartEndDate(),
      omnicart.getCartActiveDurationMinutesList().toList,
      omnicart.getCartActiveDurationMinutes(),

      omnicart.getDme(),
      omnicart.getDestination(),
      omnicart.getDestinationList().toList,
      omnicart.getFlightExists(),
      omnicart.getGroundTransferExists(),
      omnicart.getInsuranceExists(),
      omnicart.getPersonalMagicExists(),
      omnicart.getRentalCarExists(),

      omnicart.getStandalonePersonalMagicInd(),
      omnicart.getStandalonePersonalMagicIndList().toList,
      omnicart.getStandalonePersonalMagicNum(),
      omnicart.getStandalonePersonalMagicNumList().toList,
      omnicart.getStandalonePersonalMagicNumMedian(),
      omnicart.getStandalonePersonalMagicNumDistinctCount(),

      omnicart.getPackageTicketDescriptionList.toList,
      omnicart.getPackageTicketDescriptionDstcnt,
      omnicart.getPackageDineExistsList().toList,
      omnicart.getPackageDineExists(),
      omnicart.getPackageRoomExistsList().toList,
      omnicart.getPackageRoomExists(),
      omnicart.getPackageRoomOnlyList().toList,
      omnicart.getPackageRoomOnly(),
      omnicart.getPackageTicket(),
      omnicart.getIsPackageList().toList,
      omnicart.getIsPackage(),
      omnicart.getPackageTicketNumDaysList().toList,
      omnicart.getPackageTicketNumDays(),
      omnicart.getPackageTicketNumDaysMedian(),
      omnicart.getPackageTicketNumDaysDstcnt(),
      omnicart.getPackageTicketParkHopperList().toList,
      omnicart.getPackageTicketParkHopper(),
      omnicart.getPackageTicketParkHopperPlusList().toList,
      omnicart.getPackageTicketParkHopperPlus(),
      omnicart.getPackageTicketAdultCntList().toList,
      omnicart.getPackageTicketAdultCnt(),
      omnicart.getPackageTicketAdultCntMax(),
      omnicart.getPackageTicketAdultCntMedian(),
      omnicart.getPackageTicketAdultCntDstcnt(),
      omnicart.getPackageTicketChildCntList().toList,
      omnicart.getPackageTicketChildCnt(),
      omnicart.getPackageTicketChildCntMax(),
      omnicart.getPackageTicketChildCntMedian(),
      omnicart.getPackageTicketChildCntDstcnt(),

      omnicart.getPackageTicketChildMinAgeList().toList,
      omnicart.getPackageTicketChildMinAge(),
      omnicart.getPackageTicketChildMinAgeMin(),

      omnicart.getPackageTicketChildMaxAgeList().toList,
      omnicart.getPackageTicketChildMaxAge(),
      omnicart.getPackageTicketChildMaxAgeMax(),

      omnicart.getPackageTicketWaterparkList().toList,
      omnicart.getPackageTicketWaterpark(),
      omnicart.getNumRoomsList().toList,
      omnicart.getNumRooms(),
      omnicart.getNumRoomsMin(),
      omnicart.getNumRoomsMax(),
      omnicart.getNumRoomsDistinctCount(),
      omnicart.getResortNameList().toList,
      omnicart.getResortDstcnt(),
      omnicart.getRoomAdultCntList().toList,
      omnicart.getRoomAdultCnt(),
      omnicart.getRoomAdultCntMedian(),
      omnicart.getRoomAdultCntMax(),
      omnicart.getRoomAdultCntDstcnt(),
      omnicart.getRoomChildCnt(),
      omnicart.getRoomChildCntList().toList,
      omnicart.getRoomChildCntMax(),
      omnicart.getRoomChildCntDstcnt(),
      omnicart.getRoomChildCntMedian(),
      omnicart.getRoomChildMinAge(),
      omnicart.getRoomChildMinAgeList().toList,
      omnicart.getRoomChildMinAgeDistinctCount(),
      omnicart.getRoomChildMinAgeMedian(),
      omnicart.getRoomChildMinAgeMin(),
      omnicart.getRoomChildMaxAge(),
      omnicart.getRoomChildMaxAgeList().toList,
      omnicart.getRoomChildMaxAgeDistinctCount(),
      omnicart.getRoomChildMaxAgeMedian(),
      omnicart.getRoomChildMaxAgeMax(),

      omnicart.getRoomNumDaysList().toList,
      omnicart.getRoomNumDaysMin(),
      omnicart.getRoomNumDaysMean(),
      omnicart.getRoomNumDaysMax(),

      omnicart.getIsStandalone(),
      omnicart.getIsStandaloneList().toList,
      omnicart.getStandaloneTicketDescriptionList().toList,
      omnicart.getStandaloneTicketDescriptionDstcnt,
      omnicart.getStandaloneTicketNumList().toList,
      omnicart.getStandaloneTicketNumMedian(),
      omnicart.getStandaloneTicketNumDistinctCount(),
      omnicart.getStandaloneTicketNumDaysList().toList,
      omnicart.getStandaloneTicketNumDaysMedian(),
      omnicart.getStandaloneTicketNumDaysDistinctCount(),
      omnicart.getStandaloneTicketParkHopper(),
      omnicart.getStandaloneTicketParkHopperList().toList,
      omnicart.getStandaloneTicketParkHopperPlus(),
      omnicart.getStandaloneTicketParkHopperPlusList().toList,
      omnicart.getStandaloneTicketAdultCountList().toList,
      omnicart.getStandaloneTicketAdultCount(),
      omnicart.getStandaloneTicketAdultCntMax(),
      omnicart.getStandaloneTicketAdultCountMedian(),
      omnicart.getStandaloneTicketAdultCountDistinctCount(),
      omnicart.getStandaloneTicketChildCntList.toList,
      omnicart.getStandaloneTicketChildCnt(),
      omnicart.getStandaloneTicketChildCntMax(),
      omnicart.getStandaloneTicketChildCntMedian(),
      omnicart.getStandaloneTicketChildCntDstcnt(),

      omnicart.getAdultCountMax(),
      omnicart.getAdultCountMedian,
      omnicart.getAdultCountDistinctCount,
      omnicart.getChildCountMax,
      omnicart.getChildCountMedian,
      omnicart.getChildCountDistinctCount,

      omnicart.getStandaloneTicketChildMinAge(),
      omnicart.getStandaloneTicketChildMinAgeList().toList,
      omnicart.getStandaloneTicketChildMinAgeDistinctCount(),
      omnicart.getStandaloneTicketChildMinAgeMin(),
      omnicart.getStandaloneTicketChildMinAgeMedian(),

      omnicart.getStandaloneTicketChildMaxAge(),
      omnicart.getStandaloneTicketChildMaxAgeList().toList,
      omnicart.getStandaloneTicketChildMaxAgeDistinctCount(),
      omnicart.getStandaloneTicketChildMaxAgeMax(),
      omnicart.getStandaloneTicketChildMaxAgeMedian(),

      omnicart.getStandaloneTicketWaterParkList().toList,
      omnicart.getStandaloneTicketWaterPark()

    )
  }


  def convert(omnicartHbase: OmnicartHbase): Omnicart = {
    val omnicart = new Omnicart()
    omnicart.setAreaStartDateList(mutable.ListBuffer(omnicartHbase.areaStartDateList:_*))
    omnicart.setAreaStartDateDistinctCount(omnicartHbase.areaStartDateDistinctCount)
    omnicart.setAreaStartDateMonth(omnicartHbase.areaStartDateMonth)
    omnicart.setAreaStartDateMonthList(mutable.ListBuffer(omnicartHbase.areaStartDateMonthList:_*))
    omnicart.setDaysTripDuration(omnicartHbase.daysTripDuration)
    omnicart.setDaysTripDurationList(mutable.ListBuffer(omnicartHbase.daysTripDurationList:_*))
    omnicart.setDaysTripDurationDistinctCount(omnicartHbase.daysTripDurationDistinctCount)
    omnicart.setDaysTripDurationMedian(omnicartHbase.daysTripDurationMedian)

    omnicart.setEventNum(omnicartHbase.eventNum)
    omnicart.setSwid(omnicartHbase.swid)
    omnicart.setSwidList(mutable.ListBuffer(omnicartHbase.swidList:_*))
    omnicart.setCartStatus(omnicartHbase.cartStatus)
    omnicart.setCartStatusList(mutable.ListBuffer(omnicartHbase.cartStatusList:_*))
    omnicart.setCartSwidExists(omnicartHbase.cartSwidExists)
    omnicart.setCartTotalAmountList(mutable.ListBuffer(omnicartHbase.cartTotalAmountList:_*))
    omnicart.setCartTotalAmountMean(omnicartHbase.cartTotalAmountMean)
    omnicart.setCartTotalAmountMedian(omnicartHbase.cartTotalAmountMedian)
    omnicart.setCartTotalAmountDistinctCount(omnicartHbase.cartTotalAmountDistinctCount)
    omnicart.setCartCookieId(omnicartHbase.cartCookieId)
    omnicart.setCartCookieIdExistsList(mutable.ListBuffer(omnicartHbase.cartCookieIdExistsList:_*))
    omnicart.setCartCookieIdDistinctCount(omnicartHbase.cartCookieIdDistinctCount)
    omnicart.setCartCookieIdExists(omnicartHbase.cartCookieIdExists)
    omnicart.setCartSecondaryGuests(omnicartHbase.cartSecondaryGuests)
    omnicart.setCartSecondaryGuestsList(mutable.ListBuffer(omnicartHbase.cartSecondaryGuestsList:_*))
    omnicart.setCartSecondaryGuestsDistinctCount(omnicartHbase.cartSecondaryGuestsDistinctCount)

    omnicart.setCartStartDate(omnicartHbase.cartStartDate)
    omnicart.setCartStartDateList(mutable.ListBuffer(omnicartHbase.cartStartDateList:_*))
    omnicart.setDaysCreatedAheadOfTripList(mutable.ListBuffer(omnicartHbase.daysCreatedAheadOfTripList:_*))
    omnicart.setDaysCreatedAheadOfTripMedian(omnicartHbase.daysCreatedAheadOfTripMedian)
    omnicart.setDaysCreatedAheadOfTripDistinctCount(omnicartHbase.daysCreatedAheadOfTripDistinctCount)
    omnicart.setCreateDatetimeMonth(omnicartHbase.createDatetimeMonth)

    omnicart.setLastModifiedDateList(mutable.ListBuffer(omnicartHbase.lastModifiedDateList:_*))
    omnicart.setDaysModifiedAheadOfTripList(mutable.ListBuffer(omnicartHbase.daysModifiedAheadOfTripList:_*))
    omnicart.setDaysModifiedAheadOfTripMedian(omnicartHbase.daysModifiedAheadOfTripMedian)
    omnicart.setDaysModifiedAheadOfTripDistinctCount(omnicartHbase.daysModifiedAheadOfTripDistinctCount)
    omnicart.setCartEndDate(omnicartHbase.cartEndDate)
    omnicart.setCartActiveDurationMinutesList(mutable.ListBuffer(omnicartHbase.cartActiveDurationMinutesList:_*))
    omnicart.setCartActiveDurationMinutes(omnicartHbase.cartActiveDurationMinutes)

    omnicart.setDme(omnicartHbase.dme)
    omnicart.setDestination(omnicartHbase.destination)
    omnicart.setDestinationList(mutable.ListBuffer(omnicartHbase.destinationList:_*))
    omnicart.setFlightExists(omnicartHbase.flightExists)
    omnicart.setGroundTransferExists(omnicartHbase.groundTransferExists)
    omnicart.setInsuranceExists(omnicartHbase.insuranceExists)
    omnicart.setPersonalMagicExists(omnicartHbase.personalMagicExists)
    omnicart.setRentalCarExists(omnicartHbase.rentalCarExists)

    omnicart.setStandalonePersonalMagicInd(omnicartHbase.standalonePersonalMagicInd)
    omnicart.setStandalonePersonalMagicIndList(mutable.ListBuffer(omnicartHbase.standalonePersonalMagicIndList:_*))
    omnicart.setStandalonePersonalMagicNum(omnicartHbase.standalonePersonalMagicNum)
    omnicart.setStandalonePersonalMagicNumList(mutable.ListBuffer(omnicartHbase.standalonePersonalMagicNumList:_*))
    omnicart.setStandalonePersonalMagicNumMedian(omnicartHbase.standalonePersonalMagicNumMedian)
    omnicart.setStandalonePersonalMagicNumDistinctCount(omnicartHbase.standalonePersonalMagicNumDistinctCount)

    omnicart.setPackageTicketDescriptionList(mutable.ListBuffer(omnicartHbase.packageTicketDescriptionList:_*))
    omnicart.setPackageTicketDescriptionDstcnt(omnicartHbase.packageTicketDescriptionDstcnt)
    omnicart.setPackageDineExistsList(mutable.ListBuffer(omnicartHbase.packageDineExistsList:_*))
    omnicart.setPackageDineExists(omnicartHbase.packageDineExists)
    omnicart.setPackageRoomExistsList(mutable.ListBuffer(omnicartHbase.packageRoomExistsList:_*))
    omnicart.setPackageRoomExists(omnicartHbase.packageRoomExists)
    omnicart.setPackageRoomOnlyList(mutable.ListBuffer(omnicartHbase.packageRoomOnlyList:_*))
    omnicart.setPackageRoomOnly(omnicartHbase.packageRoomOnly)
    omnicart.setPackageTicket(omnicartHbase.packageTicket)
    omnicart.setIsPackageList(mutable.ListBuffer(omnicartHbase.isPackageList:_*))
    omnicart.setIsPackage(omnicartHbase.isPackage)
    omnicart.setPackageTicketNumDaysList(mutable.ListBuffer(omnicartHbase.packageTicketNumDaysList:_*))
    omnicart.setPackageTicketNumDays(omnicartHbase.packageTicketNumDays)
    omnicart.setPackageTicketNumDaysMedian(omnicartHbase.packageTicketNumDaysMedian)
    omnicart.setPackageTicketNumDaysDstcnt(omnicartHbase.packageTicketNumDaysDstcnt)
    omnicart.setPackageTicketParkHopperList(mutable.ListBuffer(omnicartHbase.packageTicketParkHopperList:_*))
    omnicart.setPackageTicketParkHopper(omnicartHbase.packageTicketParkHopper)
    omnicart.setPackageTicketParkHopperPlusList(mutable.ListBuffer(omnicartHbase.packageTicketParkHopperPlusList:_*))
    omnicart.setPackageTicketParkHopperPlus(omnicartHbase.packageTicketParkHopperPlus)
    omnicart.setPackageTicketAdultCntList(mutable.ListBuffer(omnicartHbase.packageTicketAdultCntList:_*))
    omnicart.setPackageTicketAdultCnt(omnicartHbase.packageTicketAdultCnt)
    omnicart.setPackageTicketAdultCntMax(omnicartHbase.packageTicketAdultCntMax)
    omnicart.setPackageTicketAdultCntMedian(omnicartHbase.packageTicketAdultCntMedian)
    omnicart.setPackageTicketAdultCntDstcnt(omnicartHbase.packageTicketAdultCntDstcnt)
    omnicart.setPackageTicketChildCntList(mutable.ListBuffer(omnicartHbase.packageTicketChildCntList:_*))
    omnicart.setPackageTicketChildCnt(omnicartHbase.packageTicketChildCnt)
    omnicart.setPackageTicketChildCntMax(omnicartHbase.packageTicketChildCntMax)
    omnicart.setPackageTicketChildCntMedian(omnicartHbase.packageTicketChildCntMedian)
    omnicart.setPackageTicketChildCntDstcnt(omnicartHbase.packageTicketChildCntDstcnt)

    omnicart.setPackageTicketChildMinAgeList(mutable.ListBuffer(omnicartHbase.packageTicketChildMinAgeList:_*))
    omnicart.setPackageTicketChildMinAge(omnicartHbase.packageTicketChildMinAge)
    omnicart.setPackageTicketChildMinAgeMin(omnicartHbase.packageTicketChildMinAgeMin)

    omnicart.setPackageTicketChildMaxAgeList(mutable.ListBuffer(omnicartHbase.packageTicketChildMaxAgeList:_*))
    omnicart.setPackageTicketChildMaxAge(omnicartHbase.packageTicketChildMaxAge)
    omnicart.setPackageTicketChildMaxAgeMax(omnicartHbase.packageTicketChildMaxAgeMax)

    omnicart.setPackageTicketWaterparkList(mutable.ListBuffer(omnicartHbase.packageTicketWaterparkList:_*))
    omnicart.setPackageTicketWaterpark(omnicartHbase.packageTicketWaterpark)
    omnicart.setNumRoomsList(mutable.ListBuffer(omnicartHbase.numRoomsList:_*))
    omnicart.setNumRooms(omnicartHbase.numRooms)
    omnicart.setNumRoomsMin(omnicartHbase.numRoomsMin)
    omnicart.setNumRoomsMax(omnicartHbase.numRoomsMax)
    omnicart.setNumRoomsDistinctCount(omnicartHbase.numRoomsDistinctCount)
    omnicart.setResortNameList(mutable.ListBuffer(omnicartHbase.resortNameList:_*))
    omnicart.setResortDstcnt(omnicartHbase.resortDstcnt)
    omnicart.setRoomAdultCntList(mutable.ListBuffer(omnicartHbase.roomAdultCntList:_*))
    omnicart.setRoomAdultCnt(omnicartHbase.roomAdultCnt)
    omnicart.setRoomAdultCntMedian(omnicartHbase.roomAdultCntMedian)
    omnicart.setRoomAdultCntMax(omnicartHbase.roomAdultCntMax)
    omnicart.setRoomAdultCntDstcnt(omnicartHbase.roomAdultCntDstcnt)
    omnicart.setRoomChildCnt(omnicartHbase.roomChildCnt)
    omnicart.setRoomChildCntList(mutable.ListBuffer(omnicartHbase.roomChildCntList:_*))
    omnicart.setRoomChildCntMax(omnicartHbase.roomChildCntMax)
    omnicart.setRoomChildCntDstcnt(omnicartHbase.roomChildCntDstcnt)
    omnicart.setRoomChildCntMedian(omnicartHbase.roomChildCntMedian)
    omnicart.setRoomChildMinAge(omnicartHbase.roomChildMinAge)
    omnicart.setRoomChildMinAgeList(mutable.ListBuffer(omnicartHbase.roomChildMinAgeList:_*))
    omnicart.setRoomChildMinAgeDistinctCount(omnicartHbase.roomChildMinAgeDistinctCount)
    omnicart.setRoomChildMinAgeMedian(omnicartHbase.roomChildMinAgeMedian)
    omnicart.setRoomChildMinAgeMin(omnicartHbase.roomChildMinAgeMin)
    omnicart.setRoomChildMaxAge(omnicartHbase.roomChildMaxAge)
    omnicart.setRoomChildMaxAgeList(mutable.ListBuffer(omnicartHbase.roomChildMaxAgeList:_*))
    omnicart.setRoomChildMaxAgeDistinctCount(omnicartHbase.roomChildMaxAgeDistinctCount)
    omnicart.setRoomChildMaxAgeMedian(omnicartHbase.roomChildMaxAgeMedian)
    omnicart.setRoomChildMaxAgeMax(omnicartHbase.roomChildMaxAgeMax)

    omnicart.setRoomNumDaysList(mutable.ListBuffer(omnicartHbase.roomNumDaysList:_*))
    omnicart.setRoomNumDaysMin(omnicartHbase.roomNumDaysMin)
    omnicart.setRoomNumDaysMean(omnicartHbase.roomNumDaysMean)
    omnicart.setRoomNumDaysMax(omnicartHbase.roomNumDaysMax)

    omnicart.setIsStandalone(omnicartHbase.isStandalone)
    omnicart.setIsStandaloneList(mutable.ListBuffer(omnicartHbase.isStandaloneList:_*))
    omnicart.setStandaloneTicketDescriptionList(mutable.ListBuffer(omnicartHbase.standaloneTicketDescriptionList:_*))
    omnicart.setStandaloneTicketDescriptionDstcnt(omnicartHbase.standaloneTicketDescriptionDstcnt)
    omnicart.setStandaloneTicketNumList(mutable.ListBuffer(omnicartHbase.standaloneTicketNumList:_*))
    omnicart.setStandaloneTicketNumMedian(omnicartHbase.standaloneTicketNumMedian)
    omnicart.setStandaloneTicketNumDistinctCount(omnicartHbase.standaloneTicketNumDistinctCount)
    omnicart.setStandaloneTicketNumDaysList(mutable.ListBuffer(omnicartHbase.standaloneTicketNumDaysList:_*))
    omnicart.setStandaloneTicketNumDaysMedian(omnicartHbase.standaloneTicketNumDaysMedian)
    omnicart.setStandaloneTicketNumDaysDistinctCount(omnicartHbase.standaloneTicketNumDaysDistinctCount)
    omnicart.setStandaloneTicketParkHopper(omnicartHbase.standaloneTicketParkHopper)
    omnicart.setStandaloneTicketParkHopperList(mutable.ListBuffer(omnicartHbase.standaloneTicketParkHopperList:_*))
    omnicart.setStandaloneTicketParkHopperPlus(omnicartHbase.standaloneTicketParkHopperPlus)
    omnicart.setStandaloneTicketParkHopperPlusList(mutable.ListBuffer(omnicartHbase.standaloneTicketParkHopperPlusList:_*))
    omnicart.setStandaloneTicketAdultCountList(mutable.ListBuffer(omnicartHbase.standaloneTicketAdultCountList:_*))
    omnicart.setStandaloneTicketAdultCount(omnicartHbase.standaloneTicketAdultCount)
    omnicart.setStandaloneTicketAdultCntMax(omnicartHbase.standaloneTicketAdultCntMax)
    omnicart.setStandaloneTicketAdultCountMedian(omnicartHbase.standaloneTicketAdultCountMedian)
    omnicart.setStandaloneTicketAdultCountDistinctCount(omnicartHbase.standaloneTicketAdultCountDistinctCount)
    omnicart.setStandaloneTicketChildCntList(mutable.ListBuffer(omnicartHbase.standaloneTicketChildCntList:_*))
    omnicart.setStandaloneTicketChildCnt(omnicartHbase.standaloneTicketChildCnt)
    omnicart.setStandaloneTicketChildCntMax(omnicartHbase.standaloneTicketChildCntMax)
    omnicart.setStandaloneTicketChildCntMedian(omnicartHbase.standaloneTicketChildCntMedian)
    omnicart.setStandaloneTicketChildCntDstcnt(omnicartHbase.standaloneTicketChildCntDstcnt)

    omnicart.setAdultCountMax(omnicartHbase.adultCountMax)
    omnicart.setAdultCountMedian(omnicartHbase.adultCountMedian)
    omnicart.setAdultCountDistinctCount(omnicartHbase.adultCountDistinctCount)
    omnicart.setChildCountMax(omnicartHbase.childCountMax)
    omnicart.setChildCountMedian(omnicartHbase.childCountMedian)
    omnicart.setChildCountDistinctCount(omnicartHbase.childCountDistinctCount)

    omnicart.setStandaloneTicketChildMinAge(omnicartHbase.standaloneTicketChildMinAge)
    omnicart.setStandaloneTicketChildMinAge(omnicartHbase.standaloneTicketChildMinAge)
    omnicart.setStandaloneTicketChildMinAgeDistinctCount(omnicartHbase.standaloneTicketChildMinAgeDistinctCount)
    omnicart.setStandaloneTicketChildMinAgeMin(omnicartHbase.standaloneTicketChildMinAgeMin)
    omnicart.setStandaloneTicketChildMinAgeMedian(omnicartHbase.standaloneTicketChildMinAgeMedian)

    omnicart.setStandaloneTicketChildMaxAge(omnicartHbase.standaloneTicketChildMaxAge)
    omnicart.setStandaloneTicketChildMaxAgeList(mutable.ListBuffer(omnicartHbase.standaloneTicketChildMaxAgeList:_*))
    omnicart.setStandaloneTicketChildMaxAgeDistinctCount(omnicartHbase.standaloneTicketChildMaxAgeDistinctCount)
    omnicart.setStandaloneTicketChildMaxAgeMax(omnicartHbase.standaloneTicketChildMaxAgeMax)
    omnicart.setStandaloneTicketChildMaxAgeMedian(omnicartHbase.standaloneTicketChildMaxAgeMedian)

    omnicart.setStandaloneTicketWaterParkList(mutable.ListBuffer(omnicartHbase.standaloneTicketWaterParkList:_*))
    omnicart.setStandaloneTicketWaterPark(omnicartHbase.standaloneTicketWaterPark)

    return omnicart
  }


  /**
    * Converting mutable.MutableList[OmnicartHbase] to JSON string
    *
    * @param omnicartList
    * @return
    */
  def toJson(omnicartList: mutable.ListBuffer[OmnicartHbase]): String = {
    implicit val formats = DefaultFormats
    return write(omnicartList)
  }


  /**
    * Converting JSON string to mutable.MutableList[OmnicartHbase]
    *
    * @param json
    * @return omnicartHbaseList
    */
  def toOmnicartHbaseList(json: String): mutable.ListBuffer[OmnicartHbase] = {
    implicit val formats = DefaultFormats
    return mutable.ListBuffer(parse(json).extract[List[OmnicartHbase]]:_*)
  }

}
