package com.kasianov.sergei.omaloma.ui.publicholidays

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasianov.sergei.omaloma.Utils.Event
import com.kasianov.sergei.omaloma.data.source.remote.NetworkUtils
import com.kasianov.sergei.omaloma.data.source.remote.dto.PublicHolidayDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class PubHolViewModel : ViewModel() {

    private val _publicHolidays by lazy { MutableLiveData<List<PublicHolidayDto>>() }

    val publicHolidays: LiveData<List<PublicHolidayDto>>
        get() = _publicHolidays

    private val _selectedPubHoliday by lazy { MutableLiveData<Event<PublicHolidayDto>>() }

    val selectedPubHoliday: LiveData<Event<PublicHolidayDto>>
        get() = _selectedPubHoliday

    private val _loadingSpinner = MutableLiveData<Boolean>()

    val loadingSpinner: LiveData<Boolean>
        get() = _loadingSpinner

    private val _errorMsg by lazy { MutableLiveData<String>() }

    val errorMsg: LiveData<String>
        get() = _errorMsg

    val apiService = NetworkUtils.pubHolApiService

    fun loadPubHolidays() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getPublicHolidays("2020", "FI")
                }
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) _publicHolidays.postValue(body)
                } else if (response.errorBody() != null) {
                    val errorBody = response.errorBody()
                    _errorMsg.postValue("Error body:  ${errorBody?.string()}")
                } else {
                    _errorMsg.postValue("${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _errorMsg.postValue(e.message ?: e.toString())
            }
        }
    }

    fun setHolidaySelected(position: Int) {
       _publicHolidays.value?.get(position)?.let {
           _selectedPubHoliday.value = Event(it)
       }

    }

}

