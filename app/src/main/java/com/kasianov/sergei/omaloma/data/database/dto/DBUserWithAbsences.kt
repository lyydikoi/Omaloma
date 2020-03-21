package com.kasianov.sergei.omaloma.data.database.dto

import androidx.room.Embedded
import androidx.room.Relation

data class DBUserWithAbsences(
    @Embedded val user: DBUser,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val absences: List<DBAbsence>
)
