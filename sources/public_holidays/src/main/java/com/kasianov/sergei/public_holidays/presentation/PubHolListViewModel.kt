package com.kasianov.sergei.public_holidays.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kasianov.sergei.core_api.extentions.Event
import com.kasianov.sergei.core.ui.BaseViewModel
import com.kasianov.sergei.core_api.model.dto.PublicHolidayDTO
import javax.inject.Inject

// TODO: move this to settings
const val DEFAULT_YEAR = "2020"
const val DEFAULT_COUNTRY = "FI"

class PubHolListViewModel @Inject constructor() : BaseViewModel() {

}
