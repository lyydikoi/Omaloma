package com.kasianov.sergei.absence.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kasianov.sergei.absence.data.AbsenceRepo
import com.kasianov.sergei.core.ui.BaseViewModel
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO
import com.kasianov.sergei.core_api.utils.CalcDateUtils
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import javax.inject.Inject

class AbsenceDetailsViewModel @Inject internal constructor(
    private val absenceRepo: AbsenceRepo,
    private val calcDateUtils: CalcDateUtils
): BaseViewModel() {

    private val _selectedAbsence by lazy { MutableLiveData<AbsenceDTO>() }
    val selectedAbsence: LiveData<AbsenceDTO>
        get() = _selectedAbsence

    fun getAbsence(createdMillis: String) = launchDataLoad {
        calcDateUtils.millisStringToLong(createdMillis)?.let { millis ->
            launchDataLoad {
                absenceRepo.getAbsence(millis).let { absence ->
                    _selectedAbsence.postValue(absence)
                }
            }
        }
    }

    fun setAbsenceDates(startDate: LocalDate, endDate: LocalDate?) {
        val currentAbsence: AbsenceDTO? = selectedAbsence.value
        currentAbsence?.let { absence ->
            absence.startMillis = startDate.toMillis()
            endDate?.let { absence.endMillis = it.toMillis() }
            _selectedAbsence.postValue(absence)
            launchDataLoad {
                absenceRepo.updateAbsence(absence)
            }
        }
    }

    private fun LocalDate.toMillis(zone: ZoneId = ZoneId.systemDefault()) =
        this.atStartOfDay(zone).toEpochSecond()
}
