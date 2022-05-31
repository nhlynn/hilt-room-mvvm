package com.nhlynn.hilt_room_mvvm.utils

import java.text.SimpleDateFormat
import java.util.*


object MyUtil {
    fun convertDate(milliSecond: Long): String {
        val dates = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        return dates.format(Date(milliSecond))
    }

    fun convertDateTime(milliSecond: Long): String {
        val dates = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.ENGLISH)
        return dates.format(Date(milliSecond))
    }
}