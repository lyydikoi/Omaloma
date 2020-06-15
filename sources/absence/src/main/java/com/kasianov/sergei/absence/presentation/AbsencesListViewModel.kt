package com.kasianov.sergei.absence.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kasianov.sergei.absence.data.AbsenceRepo
import com.kasianov.sergei.core.ui.BaseViewModel
import com.kasianov.sergei.core_api.extentions.Event
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO
import com.kasianov.sergei.core_api.model.dto.PublicHolidayDTO
import com.kasianov.sergei.core_api.utils.CalcDateUtils
import javax.inject.Inject

class AbsencesListViewModel @Inject internal constructor(
    private val absenceRepo: AbsenceRepo,
    calcDateUtils: CalcDateUtils
): BaseViewModel() {

    init {
        fetchAbsences(calcDateUtils.getDefaultYear())
    }

    private val _absencesList by lazy { MutableLiveData<List<AbsenceDTO>>() }
    val absencesList: LiveData<List<AbsenceDTO>>
        get() = _absencesList

    private val _selectedAbsence by lazy { MutableLiveData<AbsenceDTO>() }
    val selectedAbsence: LiveData<Event<AbsenceDTO>> = Transformations.map(_selectedAbsence) {
        Event<AbsenceDTO>(it)
    }

    private fun fetchAbsences(year: String) = launchDataLoad {
        absenceRepo.getAllAbsences(year).let {
            _absencesList.postValue(it)
        }
    }

    fun setAbsenceSelected(position: Int) {
        absencesList.value?.get(position)?.let { _selectedAbsence.value = it }
    }
}
