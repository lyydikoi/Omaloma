package com.kasianov.sergei.absence.data

import androidx.lifecycle.LiveData
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO

interface AbsenceRepo {

    suspend fun getAbsence(createdMillis: Long): AbsenceDTO

    suspend fun saveAbsence(absence: AbsenceDTO)

    suspend fun getAllAbsences(year: String): List<AbsenceDTO>

    suspend fun updateAbsence(absence: AbsenceDTO)

    suspend fun deleteAbsence(absence: AbsenceDTO)

}
