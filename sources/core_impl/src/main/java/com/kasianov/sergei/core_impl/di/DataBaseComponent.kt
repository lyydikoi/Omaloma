package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.AppProvider
import com.kasianov.sergei.core_api.database.DataBaseProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [DataBaseModule::class]
)
interface DataBaseComponent : DataBaseProvider