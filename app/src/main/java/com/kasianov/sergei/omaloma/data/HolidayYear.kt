package com.kasianov.sergei.omaloma.data

import org.threeten.bp.LocalDate

data class HolidayYear (
    val year: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val isClosed: Boolean,
    val isTrial: Boolean,
    val holidayMonths: ArrayList<HolidayMonth>
)