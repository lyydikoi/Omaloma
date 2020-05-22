package com.kasianov.sergei.core_api.utils

import java.time.DayOfWeek

interface CalcDateUtils {

    fun getFormattedDate(millisec: String) : String

    fun daysOfWeekFromLocale(): Array<DayOfWeek>

}