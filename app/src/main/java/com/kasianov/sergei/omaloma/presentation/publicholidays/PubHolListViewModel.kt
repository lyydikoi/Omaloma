package com.kasianov.sergei.omaloma.presentation.publicholidays

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kasianov.sergei.omaloma.core.BaseViewModel
import com.kasianov.sergei.omaloma.core.di.FragmentScope
import com.kasianov.sergei.omaloma.core.extentions.Event
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.usecases.publicholusecases.GetAllPublicHolidaysUseCase
import javax.inject.Inject

const val DEFAULT_YEAR = "2020"
const val DEFAULT_COUNTRY = "FI"

class PubHolListViewModel @Inject constructor(
    private val getAllPublicHolidaysUseCase: GetAllPublicHolidaysUseCase
) : BaseViewModel() {

    init {
        fetchPublicHolidays(DEFAULT_YEAR, DEFAULT_COUNTRY)
    }

    private val _publicHolidays by lazy { MutableLiveData<List<PublicHoliday>>() }
    val publicHolidays: LiveData<List<PublicHoliday>>
        get() = _publicHolidays

    private val _selectedPubHoliday by lazy { MutableLiveData<PublicHoliday>() }
    val selectedPubHoliday: LiveData<Event<PublicHoliday>>
            = Transformations.map(_selectedPubHoliday) { Event<PublicHoliday>(it) }

    private fun fetchPublicHolidays(year: String, countyCode: String) =
        launchDataLoad {
            handleResponse(getAllPublicHolidaysUseCase(year, countyCode), _publicHolidays)
        }

    fun setHolidaySelected(position: Int) {
       publicHolidays.value?.get(position)?.let { _selectedPubHoliday.value = it }
    }

}

