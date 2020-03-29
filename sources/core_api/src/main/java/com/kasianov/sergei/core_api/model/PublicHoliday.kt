package com.kasianov.sergei.core_api.model

data class PublicHoliday (
    val name: String,
    val localName: String,
    val dateFormatted: String,
    val countryCode: String
)