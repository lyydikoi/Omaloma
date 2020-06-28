package com.kasianov.sergei.core_impl.di

import com.kasianov.sergei.core_api.utils.CalcDateUtils
import com.kasianov.sergei.core_api.utils.GraphicsUtils
import com.kasianov.sergei.core_impl.utils.CalcDatesUtilsImpl
import com.kasianov.sergei.core_impl.utils.GraphicsUtilsImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UtilsModule {

    @Binds
    abstract fun bindCalcDateUtils(calcDateUtils: CalcDatesUtilsImpl): CalcDateUtils

    @Binds
    abstract fun bindGraphicsUtils(graphicsUtils: GraphicsUtilsImpl): GraphicsUtils
}
