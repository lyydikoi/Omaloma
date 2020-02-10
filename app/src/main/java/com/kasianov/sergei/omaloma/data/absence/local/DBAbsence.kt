package com.kasianov.sergei.omaloma.data.absence.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "absence_table")
data class DBAbsence(
    val title: String,
    val type: String,
    val status: String,
    val userId: String,
    @PrimaryKey
    @ColumnInfo(name = "created_at")
    val createdAt: Long,
    @ColumnInfo(name = "updated_at")
    val updateAt: Long,
    @ColumnInfo(name = "start_date")
    val startDate: Long,
    @ColumnInfo(name = "end_date")
    val endDate: Long
)