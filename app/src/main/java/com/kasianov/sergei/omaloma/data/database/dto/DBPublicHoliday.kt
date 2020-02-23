package com.kasianov.sergei.omaloma.data.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "public_holiday_table")
data class DBPublicHoliday(
    @PrimaryKey
    val name: String,
    val localName: String,
    val dateFormatted: String,
    val countryCode: String
)