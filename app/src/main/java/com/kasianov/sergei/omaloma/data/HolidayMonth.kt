package com.kasianov.sergei.omaloma.data

data class HolidayMonth (
    val month: Int,
    val isClosed: Boolean,
    val earnedHolidaysCount: Int,
    val usedHolidaysCount: Int,
    val earnedDaysOffCount: Int,
    val workingDaysCount: Int,
    val sickLeavesCount: Int,
    val childSickDaysCount: Int,
    val parentsLeavesCount: Int
)