package com.kasianov.sergei.absence.data

import androidx.lifecycle.LiveData
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO

interface AbsenceRepo {

    suspend fun getAbsence(absenceId: Long): LiveData<AbsenceDTO>

    suspend fun saveAbsence(absence: AbsenceDTO)

    suspend fun getAllAbsences(): LiveData<AbsenceDTO>

    suspend fun updateAbsence(absence: AbsenceDTO)

    suspend fun deleteAbsence(absence: AbsenceDTO)

}
