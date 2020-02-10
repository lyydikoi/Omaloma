package com.kasianov.sergei.omaloma.data.pubholiday.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "public_holiday_table")
data class DBPublicHoliday(
    @PrimaryKey
    val id: String,
    val name: String,
    val localName: String,
    val dateFormatted: String,
    val dateMilliseconds: String,
    val countryCode: String,
    val wikiInfo: String,
    val imageUrlsList: List<String>
)