package com.kasianov.sergei.absence.presentation.absences.data

import androidx.lifecycle.LiveData
import com.kasianov.sergei.core_api.database.AbsenceDao
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO
import javax.inject.Inject

class AbsenceRepoImpl @Inject constructor(
    private val absenceDao: AbsenceDao
) : AbsenceRepo {

    override suspend fun getAbsence(absenceId: String): LiveData<AbsenceDTO> =
        absenceDao.getAbsence(absenceId)

    override suspend fun saveAbsence(absence: AbsenceDTO) = absenceDao.insertAbsence(absence)

    override suspend fun getAllAbsences(): LiveData<AbsenceDTO> = absenceDao.getAllAbsences()

    override suspend fun updateAbsence(absence: AbsenceDTO) = absenceDao.updateAbsence(absence)

    override suspend fun deleteAbsence(absence: AbsenceDTO) = absenceDao.deleteAbsence(absence)

}
