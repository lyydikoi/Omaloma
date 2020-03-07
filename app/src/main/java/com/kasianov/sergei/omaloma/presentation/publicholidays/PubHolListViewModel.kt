package com.kasianov.sergei.omaloma.presentation.publicholidays

import androidx.lifecycle.*
import com.kasianov.sergei.omaloma.core.BaseViewModel
import com.kasianov.sergei.omaloma.core.extentions.Event
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.usecases.pubholusecases.GetAllStoredPublicHolidaysUseCase
import com.kasianov.sergei.omaloma.domain.usecases.pubholusecases.LoadPublicHolidaysUseCase
import javax.inject.Inject

class PubHolListViewModel @Inject constructor(
    private val loadPublicHolidaysUseCase: LoadPublicHolidaysUseCase,
    private val getAllStoredPublicHolidaysUseCase: GetAllStoredPublicHolidaysUseCase
) : BaseViewModel() {

    val publicHolidays: LiveData<List<PublicHoliday>>
        get() = getAllStoredPublicHolidaysUseCase()

    private val _selectedPubHoliday by lazy { MutableLiveData<PublicHoliday>() }
    val selectedPubHoliday: LiveData<Event<PublicHoliday>>
            = Transformations.map(_selectedPubHoliday) { Event<PublicHoliday>(it) }

    fun loadPublicHolidays(year: String, countyCode: String) =
        launchDataLoad {
            handleResponse(loadPublicHolidaysUseCase(year, countyCode))
        }

    fun setHolidaySelected(position: Int) {
       publicHolidays.value?.get(position)?.let { _selectedPubHoliday.value = it }
    }

}

