package com.kasianov.sergei.omaloma.data

import org.threeten.bp.LocalDate

data class User(
    var name: String,
    var company: String,
    var startData: LocalDate
)