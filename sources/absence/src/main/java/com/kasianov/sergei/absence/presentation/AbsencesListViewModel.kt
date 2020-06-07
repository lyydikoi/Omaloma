package com.kasianov.sergei.absence.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kasianov.sergei.absence.data.AbsenceRepo
import com.kasianov.sergei.core.ui.BaseViewModel
import com.kasianov.sergei.core_api.extentions.Event
import com.kasianov.sergei.core_api.model.dto.PublicHolidayDTO
import com.kasianov.sergei.core_api.utils.CalcDateUtils
import javax.inject.Inject

class AbsencesListViewModel @Inject internal constructor(
    private val abcenseRepo: AbsenceRepo,
    private val calcDateUtils: CalcDateUtils
): BaseViewModel() {
    init {
        fetchAbsences(calcDateUtils.getDefaultYear())
    }

    /*private val _publicHolidays by lazy { MutableLiveData<List<PublicHolidayDTO>>() }
    val publicHolidays: LiveData<List<PublicHolidayDTO>>
        get() = _publicHolidays

    private val _selectedPubHoliday by lazy { MutableLiveData<PublicHolidayDTO>() }
    val selectedPubHoliday: LiveData<Event<PublicHolidayDTO>> = Transformations.map(_selectedPubHoliday) {
        Event<PublicHolidayDTO>(it)
    }

    private fun fetchAbsences(year: String) = launchDataLoad {
        handleResponse(
            publicHolidayRepo.getStoredOrRemotePublicHolidays(year, countyCode),
            _publicHolidays
        )
    }

    fun setHolidaySelected(position: Int) {
        publicHolidays.value?.get(position)?.let { _selectedPubHoliday.value = it }
    }*/
}