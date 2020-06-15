package com.kasianov.sergei.core_api.utils

import org.threeten.bp.DayOfWeek

interface CalcDateUtils {

    fun getFormattedDate(millisec: String) : String

    fun daysOfWeekFromLocale(): Array<DayOfWeek>

    fun getDefaultYear(): String

    fun millisStringToLong(millisString: String): Long?

}