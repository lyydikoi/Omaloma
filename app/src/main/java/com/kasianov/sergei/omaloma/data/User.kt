package com.kasianov.sergei.omaloma.data

import org.joda.time.LocalDate

data class User(
    var name: String,
    var company: String,
    var startData: LocalDate
)