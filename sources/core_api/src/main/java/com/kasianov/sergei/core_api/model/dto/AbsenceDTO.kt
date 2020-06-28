package com.kasianov.sergei.core_api.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "absence_table")
data class AbsenceDTO(
    var title: String = "",
    var type: String = "",
    var status: String = "",
    var userId: String = "",
    var year: String = "",
    @PrimaryKey
    @ColumnInfo(name = "created_at")
    var createdMillis: Long = 0,
    @ColumnInfo(name = "updated_at")
    var updateMillis: Long = 0,
    @ColumnInfo(name = "start_date")
    var startDate: String,
    @ColumnInfo(name = "end_date")
    var endDate: String
)
