package com.kasianov.sergei.core.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasianov.sergei.core_api.extentions.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

open class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _errorMsg by lazy { MutableLiveData<String>() }
    val errorMsg: LiveData<String>
        get() = _errorMsg

    private val _dataLoadException by lazy { MutableLiveData<Exception>() }
    val dataLoadException: LiveData<Exception>
        get() = _dataLoadException


    /**
     * Helper function to call data load function with a loading spinner and errors triggered in a snackbar.
     *
     * @param block lambda to actually load data.
     */
    fun launchDataLoad(block: suspend () -> Unit) : Job {
        return viewModelScope.launch {
            _loading.value = true
            try {
                withContext(Dispatchers.IO) {
                    block()
                }
            } catch (e: Exception) {
                e.message?.let { _errorMsg.value = it }
            } finally {
                _loading.value = false
            }
        }
    }

    fun <T> handleResponse(result: RequestResult<T>, mld: MutableLiveData<T>? = null) {
        when (result) {
            is RequestResult.Success -> mld?.postValue(result.data)
            is RequestResult.Error -> {
                _dataLoadException.postValue(result.exception)
                _errorMsg.postValue(result.exception.message)
            }
        }
    }

}