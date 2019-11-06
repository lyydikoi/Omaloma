package com.kasianov.sergei.omaloma.data

import com.kasianov.sergei.omaloma.data.entities.User
import org.threeten.bp.LocalDate

object DefaultDataSettings {
    val startDate = LocalDate.parse("2017-12-05")
    var user =
        User("1", "Sergei", "Kasianov", "1", "2017-12-05")
}