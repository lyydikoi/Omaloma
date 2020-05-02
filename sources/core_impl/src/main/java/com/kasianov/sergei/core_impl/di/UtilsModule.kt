package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.utils.CalcDateUtils
import com.kasianov.sergei.core_impl.utils.CalcDatesUtilsImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UtilsModule {

    @Binds
    abstract fun bindCalcDateUtils(calcDateUtils: CalcDatesUtilsImpl): CalcDateUtils

}