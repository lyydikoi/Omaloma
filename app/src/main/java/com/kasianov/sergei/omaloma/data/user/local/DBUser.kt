package com.kasianov.sergei.omaloma.data.user.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class DBUser(
    @PrimaryKey
    val id: String,
    val firstName: String,
    val lastName: String,
    val companyId: String,
    val startDate: Long
)