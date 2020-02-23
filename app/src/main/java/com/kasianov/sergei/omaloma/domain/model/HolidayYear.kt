package com.kasianov.sergei.omaloma.domain.model

import org.threeten.bp.LocalDate

data class HolidayYear (
    var year: Int,
    var startDate: LocalDate,
    var endDate: LocalDate,
    var isClosed: Boolean = false,
    var isTrial: Boolean = false,
    var holidayMonths: ArrayList<HolidayMonth>
)