package com.kasianov.sergei.omaloma.data

import org.threeten.bp.LocalDate

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val companyId: String,
    val startDate: String
)