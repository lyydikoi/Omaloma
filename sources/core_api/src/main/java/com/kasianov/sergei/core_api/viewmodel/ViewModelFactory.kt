package com.kasianov.sergei.core_api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val found = creators.entries.find {
            modelClass.isAssignableFrom(it.key)
        }
        val creator = found?.value
            ?: throw ClassNotFoundException("no model provided with for ${modelClass.simpleName}")
        return creator as T
    }
}
