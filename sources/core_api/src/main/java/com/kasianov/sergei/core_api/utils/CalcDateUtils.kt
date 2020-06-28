package com.kasianov.sergei.core_api.utils

import org.threeten.bp.DayOfWeek
import java.util.Date

interface CalcDateUtils {

    fun getFormattedDate(millisec: String): String

    fun daysOfWeekFromLocale(): Array<DayOfWeek>

    fun getDefaultYear(): String

    fun getCurrentDate(): Date

    fun getCurrentDateString(): String

    fun dateToNormalDateString(date: Date): String

    fun normalDateStringToDate(dateString: String): Date?

    fun millisStringToLong(millisString: String): Long?
}
