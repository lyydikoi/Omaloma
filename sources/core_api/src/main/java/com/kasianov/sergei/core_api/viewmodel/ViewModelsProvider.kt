package com.kasianov.sergei.core_api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

interface ViewModelsProvider {

    fun provideMap():  @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>

    fun provideViewModelFactory(): ViewModelProvider.Factory

}