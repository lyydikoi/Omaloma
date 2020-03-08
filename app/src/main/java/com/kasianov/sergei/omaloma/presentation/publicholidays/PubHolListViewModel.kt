package com.kasianov.sergei.omaloma.presentation.publicholidays

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kasianov.sergei.omaloma.core.BaseViewModel
import com.kasianov.sergei.omaloma.core.extentions.Event
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.usecases.GetAllStoredPublicHolidaysUseCase
import com.kasianov.sergei.omaloma.domain.usecases.LoadPublicHolidaysUseCase
import javax.inject.Inject

const val DEFAULT_YEAR = "2020"
const val DEFAULT_COUNTRY = "FI"

class PubHolListViewModel @Inject constructor(
    private val loadPublicHolidaysUseCase: LoadPublicHolidaysUseCase,
    private val getAllStoredPublicHolidaysUseCase: GetAllStoredPublicHolidaysUseCase
) : BaseViewModel() {

    val publicHolidays: LiveData<List<PublicHoliday>> = Transformations.map(getAllStoredPublicHolidaysUseCase()) {
            if (it.isEmpty()) {
                loadPublicHolidays(DEFAULT_YEAR, DEFAULT_COUNTRY)
                null
            }
            else it
        }

    private val _selectedPubHoliday by lazy { MutableLiveData<PublicHoliday>() }
    val selectedPubHoliday: LiveData<Event<PublicHoliday>>
            = Transformations.map(_selectedPubHoliday) { Event<PublicHoliday>(it) }

    private fun loadPublicHolidays(year: String, countyCode: String) =
        launchDataLoad {
            handleResponse(loadPublicHolidaysUseCase(year, countyCode))
        }

    fun setHolidaySelected(position: Int) {
       publicHolidays.value?.get(position)?.let { _selectedPubHoliday.value = it }
    }

}

