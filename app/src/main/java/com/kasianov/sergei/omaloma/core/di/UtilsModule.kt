package com.kasianov.sergei.omaloma.core.di

import com.kasianov.sergei.omaloma.presentation.utils.CalcDateUtils
import com.kasianov.sergei.omaloma.presentation.utils.CalcDatesUtilsImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UtilsModule {
    @Binds
    abstract fun bindCalcDateUtils(calcDateUtils: CalcDatesUtilsImpl): CalcDateUtils
}