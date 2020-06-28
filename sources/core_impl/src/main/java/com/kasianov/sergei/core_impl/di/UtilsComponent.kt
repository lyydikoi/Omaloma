package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.utils.UtilsProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [UtilsModule::class]
)
interface UtilsComponent : UtilsProvider
