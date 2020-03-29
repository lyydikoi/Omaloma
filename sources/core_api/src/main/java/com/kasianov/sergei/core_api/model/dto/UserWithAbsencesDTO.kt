package com.kasianov.sergei.core_api.model.dto

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithAbsencesDTO(
    @Embedded val user: UserDTO,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val absences: List<AbsenceDTO>
)
