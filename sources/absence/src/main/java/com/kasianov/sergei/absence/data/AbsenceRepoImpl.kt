package com.kasianov.sergei.absence.data

import androidx.lifecycle.LiveData
import com.kasianov.sergei.core_api.database.AbsenceDao
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO
import javax.inject.Inject

class AbsenceRepoImpl @Inject constructor(
    private val absenceDao: AbsenceDao
) : AbsenceRepo {

    override suspend fun getAbsence(createdMillis: Long): AbsenceDTO =
        absenceDao.getAbsence(createdMillis)

    override suspend fun saveAbsence(absence: AbsenceDTO) = absenceDao.insertAbsence(absence)

    override val allAbsences: LiveData<List<AbsenceDTO>> = absenceDao.getAllAbsences()

    override suspend fun updateAbsence(absence: AbsenceDTO) = absenceDao.updateAbsence(absence)

    override suspend fun deleteAbsence(absence: AbsenceDTO) = absenceDao.deleteAbsence(absence)
}
