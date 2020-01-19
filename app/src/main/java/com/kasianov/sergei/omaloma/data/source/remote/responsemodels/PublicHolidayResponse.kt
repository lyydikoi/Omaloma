package com.kasianov.sergei.omaloma.data.source.remote.responsemodels

import com.google.gson.annotations.SerializedName

data class PublicHolidayResponse(
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("localName")
    val localName: String? = null,
    @SerializedName("launchYear")
    val launchYear: Int? = null,
    @SerializedName("countryCode")
    val countryCode: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("fixed")
    val fixed: Boolean = false,
    @SerializedName("global")
    val global: Boolean = false,
    @SerializedName("counties")
    val counties: String? = null,
    @SerializedName("type")
    val type: String? = null
)