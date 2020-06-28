package com.kasianov.sergei.core_api.model.dto

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "public_holiday_table", primaryKeys = ["year", "countryCode", "name"])
data class PublicHolidayDTO(
    var year: String,
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("date")
    val date: String?,
    @SerializedName("localName")
    val localName: String?,
    @SerializedName("launchYear")
    val launchYear: Int?,
    @SerializedName("fixed")
    val fixed: Boolean?,
    @SerializedName("global")
    val global: Boolean?,
    @SerializedName("counties")
    val counties: String?,
    @SerializedName("type")
    val type: String?
)
