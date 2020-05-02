package com.kasianov.sergei.core_api.model

data class HolidayMonth (
    var month: Int,
    var isClosed: Boolean = false,
    var earnedHolidaysCount: Int = 0,
    var usedHolidaysCount: Int = 0,
    var earnedDaysOffCount: Int = 0,
    var workingDaysCount: Int = 0,
    var sickLeavesCount: Int = 0,
    var childSickDaysCount: Int = 0,
    var parentsLeavesCount: Int = 0
)