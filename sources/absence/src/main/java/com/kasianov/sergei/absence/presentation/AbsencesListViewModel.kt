package com.kasianov.sergei.absence.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kasianov.sergei.absence.data.AbsenceRepo
import com.kasianov.sergei.core.ui.BaseViewModel
import com.kasianov.sergei.core_api.extentions.Event
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO
import com.kasianov.sergei.core_api.utils.CalcDateUtils
import javax.inject.Inject

class AbsencesListViewModel @Inject internal constructor(
    absenceRepo: AbsenceRepo,
    calcDateUtils: CalcDateUtils
) : BaseViewModel() {

    val absencesList = Transformations.map(absenceRepo.allAbsences) { absencesList ->
        absencesList.filter { it.year == calcDateUtils.getDefaultYear() }
    }

    private val _selectedAbsence by lazy { MutableLiveData<AbsenceDTO>() }
    val selectedAbsence: LiveData<Event<AbsenceDTO>> = Transformations.map(_selectedAbsence) {
        Event<AbsenceDTO>(it)
    }

    fun setAbsenceSelected(position: Int) {
        absencesList.value?.get(position)?.let { _selectedAbsence.value = it }
    }
}
