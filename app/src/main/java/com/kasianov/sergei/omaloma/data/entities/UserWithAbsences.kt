package com.kasianov.sergei.omaloma.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithAbsences(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val absences: List<Absence>
)
