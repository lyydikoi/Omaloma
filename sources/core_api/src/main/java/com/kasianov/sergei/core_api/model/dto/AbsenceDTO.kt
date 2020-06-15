package com.kasianov.sergei.core_api.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "absence_table")
data class AbsenceDTO(
    val title: String,
    val type: String,
    val status: String,
    val userId: String,
    val year: String,
    @PrimaryKey
    @ColumnInfo(name = "created_at")
    val createdMillis: Long,
    @ColumnInfo(name = "updated_at")
    val updateMillis: Long,
    @ColumnInfo(name = "start_date")
    var startMillis: Long,
    @ColumnInfo(name = "end_date")
    var endMillis: Long
)