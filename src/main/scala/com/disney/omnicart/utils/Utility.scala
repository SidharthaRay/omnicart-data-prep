package com.disney.omnicart.utils

import scala.collection.mutable

object Utility {

  def median(list: mutable.MutableList[Long]): Long = {
    val sortedList = list.sortWith(_ < _)

    if (list.size % 2 == 1)
      sortedList(sortedList.size / 2)
    else {
      val (up, down) = sortedList.splitAt(list.size / 2)
      (up.last + down.head) / 2
    }
  }

  def median(list: mutable.ListBuffer[Long]): Long = {
    val sortedList = list.sortWith(_ < _)

    if (list.size % 2 == 1)
      sortedList(sortedList.size / 2)
    else {
      val (up, down) = sortedList.splitAt(list.size / 2)
      (up.last + down.head) / 2
    }
  }

  def medianInt(list: mutable.ListBuffer[Int]): Int = {
    val sortedList = list.sortWith(_ < _)

    if (list.size % 2 == 1)
      sortedList(sortedList.size / 2)
    else {
      val (up, down) = sortedList.splitAt(list.size / 2)
      (up.last + down.head) / 2
    }
  }

  def medianDouble(list: mutable.MutableList[Double]): Double = {
    val sortedList = list.sortWith(_ < _)

    if (list.size % 2 == 1)
      sortedList(sortedList.size / 2)
    else {
      val (up, down) = sortedList.splitAt(list.size / 2)
      (up.last + down.head) / 2
    }
  }

  def medianDouble(list: mutable.ListBuffer[Double]): Double = {
    val sortedList = list.sortWith(_ < _)

    if (list.size % 2 == 1)
      sortedList(sortedList.size / 2)
    else {
      val (up, down) = sortedList.splitAt(list.size / 2)
      (up.last + down.head) / 2
    }
  }

  def mean(list: mutable.MutableList[Double]): Double = {
    return Math.round((list.sum / list.size) * 100.0) / 100.0
  }

  def mean(list: mutable.ListBuffer[Double]): Double = {
    return Math.round((list.sum / list.size) * 100.0) / 100.0
  }

  def meanInt(list: mutable.ListBuffer[Int]): Double = {
    return Math.round((list.sum / list.size) * 100.0) / 100.0
  }


}
