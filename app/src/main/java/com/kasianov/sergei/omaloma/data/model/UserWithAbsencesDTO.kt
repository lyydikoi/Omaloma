package com.kasianov.sergei.omaloma.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.kasianov.sergei.omaloma.data.model.AbsenceDTO
import com.kasianov.sergei.omaloma.data.model.UserDTO

data class UserWithAbsencesDTO(
    @Embedded val user: UserDTO,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val absences: List<AbsenceDTO>
)
