package com.kasianov.sergei.omaloma.data

import org.threeten.bp.LocalDate

object DefaultDataSettings {
    val startDate = LocalDate.parse("2017-12-05")
    var user = User("Sergei", "ParkMan", startDate)
}