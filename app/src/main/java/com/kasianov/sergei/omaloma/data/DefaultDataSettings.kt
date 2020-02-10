package com.kasianov.sergei.omaloma.data

import com.kasianov.sergei.omaloma.data.user.local.DBUser
import org.threeten.bp.LocalDate

object DefaultDataSettings {
    val startDate = LocalDate.parse("2017-12-05")
    var user =
        DBUser(
            "1",
            "Sergei",
            "Kasianov",
            "1",
            3232423422423L
        )//"2017-12-05")
}