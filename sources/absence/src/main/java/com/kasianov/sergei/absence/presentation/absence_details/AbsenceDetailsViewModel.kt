package com.kasianov.sergei.absence.presentation.absence_details

import androidx.lifecycle.*
import com.kasianov.sergei.absence.data.AbsenceRepo
import com.kasianov.sergei.core.ui.BaseViewModel
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO
import com.kasianov.sergei.core_api.utils.CalcDateUtils
import javax.inject.Inject

class AbsenceDetailsViewModel @Inject internal constructor(
    private val absenceRepo: AbsenceRepo,
    private val calcDateUtils: CalcDateUtils
) : BaseViewModel() {

    private val _selectedAbsence by lazy {
        MutableLiveData(
            AbsenceDTO(
                startDate = calcDateUtils.getCurrentDateString(),
                endDate = ""
            )
        )
    }
    private val _changeDatesEvent by lazy { MutableLiveData<Boolean>() }
    private val _setDatesEvent by lazy { MutableLiveData<Boolean>() }
    private val _closeCalendarEvent by lazy { MutableLiveData<Boolean>() }
    private val _closeDetailsViewEvent by lazy { MutableLiveData<Boolean>() }

    private val _uiState: MediatorLiveData<UIModelContract.UIState> = prepareUIStateMediator()
    val uiState: LiveData<UIModelContract.UIState>
        get() = _uiState

    fun handleAction(action: UIModelContract.Action) {
        when (action) {
            is UIModelContract.Action.GetAbsence -> getAbsence(action.millisCreated)
            is UIModelContract.Action.ChangeDate -> _changeDatesEvent.postValue(true)
            is UIModelContract.Action.SetDate -> {
                _selectedAbsence.value?.also {
                    it.startDate = action.startDate
                    it.endDate = action.endDate ?: action.startDate
                }
                _setDatesEvent.postValue(true)
            }
            is UIModelContract.Action.CloseCalendar -> _closeCalendarEvent.postValue(true)
            is UIModelContract.Action.CloseDetailsView -> _closeDetailsViewEvent.postValue(true)
            is UIModelContract.Action.SaveAbsence -> saveAbsence()
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
        } ?: _changeDatesEvent.postValue(true) // User creating new absence
    }

    private fun saveAbsence() {
        _selectedAbsence.value?.let { absence ->
            val isNew = absence.createdMillis == 0L
            // TODO: change this: get values from UI to create and update absences
            absence.year = calcDateUtils.getDefaultYear()
            absence.title = "Test absence"
            absence.type = "Holiday"
            if (absence.createdMillis == 0L) {
                absence.createdMillis = calcDateUtils.getCurrentDate().time
            } else {
                absence.updateMillis = calcDateUtils.getCurrentDate().time
            }
            launchDataLoad {
                if (isNew) {
                    absenceRepo.saveAbsence(absence)
                } else absenceRepo.updateAbsence(absence)
                _closeDetailsViewEvent.postValue(true)
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
            result.value = _selectedAbsence.value?.let {
                UIModelContract.UIState.Success(it)
            }
        }
        result.addSource(_changeDatesEvent) {
            _selectedAbsence.value?.let { absence ->
                result.value = UIModelContract.UIState.ChoosingDate(
                    absence.startDate,
                    absence.endDate
                )
            }
        }
        result.addSource(_setDatesEvent) {
            _selectedAbsence.value?.let { absence ->
                result.value = UIModelContract.UIState.Success(absence)
            }
        }
        result.addSource(_closeCalendarEvent) {
            _selectedAbsence.value?.let { absence ->
                result.value = UIModelContract.UIState.Success(absence)
            }
        }
        result.addSource(_closeDetailsViewEvent) {
            result.value = UIModelContract.UIState.NavigatingBack
        }
        return result
    }
}
