package com.kasianov.sergei.omaloma.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "public_holiday_table")
data class PublicHolidayDTO(
    @PrimaryKey
    @SerializedName("date")
    val date: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("localName")
    val localName: String?,
    @SerializedName("launchYear")
    val launchYear: Int?,
    @SerializedName("countryCode")
    val countryCode: String?,
    @SerializedName("fixed")
    val fixed: Boolean,
    @SerializedName("global")
    val global: Boolean,
    @SerializedName("counties")
    val counties: String?,
    @SerializedName("type")
    val type: String?
)