package com.kasianov.sergei.omaloma.data.network.dto

import com.google.gson.annotations.SerializedName

data class PublicHolidayDTO(
    @SerializedName("date")
    val date: String?,
    @SerializedName("localName")
    val localName: String?,
    @SerializedName("launchYear")
    val launchYear: Int?,
    @SerializedName("countryCode")
    val countryCode: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("fixed")
    val fixed: Boolean,
    @SerializedName("global")
    val global: Boolean,
    @SerializedName("counties")
    val counties: String?,
    @SerializedName("type")
    val type: String?
)