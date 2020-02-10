package com.kasianov.sergei.omaloma.data.user.remote

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("company_id")
    val companyId: String,
    @SerializedName("start_date")
    val startDate: Long
)