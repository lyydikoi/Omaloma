package com.kasianov.sergei.core_api.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "absence_table")
data class AbsenceDTO(
    val title: String = "",
    val type: String = "",
    val status: String = "",
    val userId: String = "",
    val year: String = "",
    @PrimaryKey
    @ColumnInfo(name = "created_at")
    val createdMillis: Long = 0,
    @ColumnInfo(name = "updated_at")
    val updateMillis: Long = 0,
    @ColumnInfo(name = "start_date")
    var startDate: String,
    @ColumnInfo(name = "end_date")
    var endDate: String
)