package com.kasianov.sergei.omaloma.data.user.local

import androidx.room.Embedded
import androidx.room.Relation
import com.kasianov.sergei.omaloma.data.absence.local.DBAbsence

data class DBUserWithAbsences(
    @Embedded val user: DBUser,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val absences: List<DBAbsence>
)
