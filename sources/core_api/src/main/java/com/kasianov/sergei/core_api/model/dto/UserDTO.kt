package com.kasianov.sergei.core_api.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDTO(
    @PrimaryKey
    val id: String,
    val firstName: String,
    val lastName: String,
    val companyId: String,
    val startDate: Long
)
