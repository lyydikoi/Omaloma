package com.kasianov.sergei.public_holidays.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kasianov.sergei.core_api.extentions.Event
import com.kasianov.sergei.core.ui.BaseViewModel
import com.kasianov.sergei.core_api.model.dto.PublicHolidayDTO
import com.kasianov.sergei.public_holidays.data.PublicHolidaysRepo
import javax.inject.Inject

// TODO: move this to settings
const val DEFAULT_YEAR = "2020"
const val DEFAULT_COUNTRY = "FI"

class PubHolListViewModel @Inject constructor(
    private val publicHolidayRepo: PublicHolidaysRepo
) : BaseViewModel() {

    init {
        fetchPublicHolidays(DEFAULT_YEAR, DEFAULT_COUNTRY)
    }

    private val _publicHolidays by lazy { MutableLiveData<List<PublicHolidayDTO>>() }
    val publicHolidays: LiveData<List<PublicHolidayDTO>>
        get() = _publicHolidays

    private val _selectedPubHoliday by lazy { MutableLiveData<PublicHolidayDTO>() }
    val selectedPubHoliday: LiveData<Event<PublicHolidayDTO>> = Transformations.map(_selectedPubHoliday) {
        Event<PublicHolidayDTO>(it)
    }

    private fun fetchPublicHolidays(year: String, countyCode: String) = launchDataLoad {
        handleResponse(
            publicHolidayRepo.getStoredOrRemotePublicHolidays(year, countyCode),
            _publicHolidays
        )
    }

    fun setHolidaySelected(position: Int) {
       publicHolidays.value?.get(position)?.let { _selectedPubHoliday.value = it }
    }
}
