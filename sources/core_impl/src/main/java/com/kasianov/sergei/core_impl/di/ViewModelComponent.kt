package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.viewmodel.ViewModelsProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface ViewModelComponent : ViewModelsProvider