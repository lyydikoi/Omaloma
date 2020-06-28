package com.kasianov.sergei.core_api.model

import java.time.LocalDate

data class HolidayYear(
    var year: Int,
    var startDate: LocalDate,
    var endDate: LocalDate,
    var isClosed: Boolean = false,
    var isTrial: Boolean = false,
    var holidayMonths: ArrayList<HolidayMonth>
)
