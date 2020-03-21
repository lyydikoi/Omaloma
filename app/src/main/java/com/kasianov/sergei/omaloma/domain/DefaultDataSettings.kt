package com.kasianov.sergei.omaloma.domain

import com.kasianov.sergei.omaloma.data.model.UserDTO
import org.threeten.bp.LocalDate

object DefaultDataSettings {
    val startDate = LocalDate.parse("2017-12-05")
    var user =
        UserDTO(
            "1",
            "Sergei",
            "Kasianov",
            "1",
            3232423422423L
        )//"2017-12-05")
}