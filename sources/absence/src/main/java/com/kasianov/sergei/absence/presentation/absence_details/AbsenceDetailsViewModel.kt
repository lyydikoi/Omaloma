package com.kasianov.sergei.absence.presentation.absence_details

import androidx.lifecycle.*
import com.kasianov.sergei.absence.data.AbsenceRepo
import com.kasianov.sergei.core.ui.BaseViewModel
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO
import com.kasianov.sergei.core_api.utils.CalcDateUtils
import java.util.*
import javax.inject.Inject

class AbsenceDetailsViewModel @Inject internal constructor(
    private val absenceRepo: AbsenceRepo,
    private val calcDateUtils: CalcDateUtils
): BaseViewModel() {

    private val _selectedAbsence by lazy { MutableLiveData<AbsenceDTO>() }
    private val _absenceToSave = MutableLiveData(
        AbsenceDTO(
            startDate = calcDateUtils.getCurrentDateString(),
            endDate = calcDateUtils.getCurrentDateString()
        )
    )
    private val _changeDatesEvent by lazy { MutableLiveData<Boolean>() }

    private val _uiState: MediatorLiveData<UIModelContract.UIState>  = prepareUIStateMediator()
    val uiState: LiveData<UIModelContract.UIState>
        get() = _uiState

    fun handleAction(action: UIModelContract.Action) {
        when(action) {
            is UIModelContract.Action.GetAbsence -> getAbsence(action.millisCreated)
            is UIModelContract.Action.ChangeDate -> { _changeDatesEvent.postValue(true) }
            is UIModelContract.Action.SetDate -> {
                _absenceToSave.value?.also {
                    it.startDate = action.startDate
                    it.endDate = action.endDate ?: action.startDate
                }
            }
        }
    }

    private fun getAbsence(createdMillis: String?) {
        createdMillis?.let {
            // User is viewing/modifying an existing absence
            calcDateUtils.millisStringToLong(it)?.let { millis ->
                launchDataLoad {
                    absenceRepo.getAbsence(millis).let { absence ->
                        _selectedAbsence.postValue(absence)
                    }
                }
            }
        } ?: _selectedAbsence.postValue(
            // User is creating a new absence
            AbsenceDTO(
                startDate = calcDateUtils.getCurrentDateString(),
                endDate = calcDateUtils.getCurrentDateString()
            )
        )
    }

    fun setAbsenceDates(startDate: Date, endDate: Date?) {
        val currentAbsence: AbsenceDTO? = _selectedAbsence.value
        currentAbsence?.let { absence ->
            absence.startDate = calcDateUtils.dateToNormalDateString(startDate)
            endDate?.let { absence.endDate = calcDateUtils.dateToNormalDateString(endDate) }
            _selectedAbsence.postValue(absence)
            launchDataLoad {
                absenceRepo.updateAbsence(absence)
            }
        }
    }

    private fun prepareUIStateMediator(): MediatorLiveData<UIModelContract.UIState> {
        val result = MediatorLiveData<UIModelContract.UIState>()

        result.addSource(loading) {
            result.value = UIModelContract.UIState.Loading
        }
        result.addSource(errorMsg) {
            errorMsg.value?.let {
                result.value = UIModelContract.UIState.Failure(it)
            }
        }
        result.addSource(_selectedAbsence) {
            result.value = _selectedAbsence.value?.let { absence ->
                if (absence.createdMillis == 0L) {
                    UIModelContract.UIState.ChoosingDate(
                        absence.startDate,
                        absence.endDate
                    )
                } else UIModelContract.UIState.Success(it)
            }
        }
        result.addSource(_changeDatesEvent) {
            _absenceToSave.value?.let { absence ->
                result.value = UIModelContract.UIState.ChoosingDate(
                    absence.startDate,
                    absence.endDate
                )
            }
        }

        return result
    }
}
