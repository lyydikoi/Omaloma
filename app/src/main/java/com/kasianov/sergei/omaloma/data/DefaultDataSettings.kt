package com.kasianov.sergei.omaloma.data

import org.joda.time.LocalDate

object DefaultDataSettings {
    val startDate = LocalDate.parse("2017-12-05")
    var user = User("Sergei", "ParkMan", startDate)
}