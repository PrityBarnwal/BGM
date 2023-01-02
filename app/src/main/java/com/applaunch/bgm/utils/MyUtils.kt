package com.applaunch.bgm.utils

import java.text.SimpleDateFormat
import java.util.*

object MyUtils {

    private val DAY_FORMAT = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.ENGLISH)
    private val SIMPLE_DATE_T_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)

    private fun convertDateTime(
        fromFormat: SimpleDateFormat,
        toFormat: SimpleDateFormat,
        date: String?
    ): String {
        return try {
            if (date?.length!! > 0) toFormat.format(fromFormat.parse(date))
            else ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    @JvmStatic
    fun convertDate(date: String) :String{
        return convertDateTime(SIMPLE_DATE_T_FORMAT, DAY_FORMAT, date)
    }
}