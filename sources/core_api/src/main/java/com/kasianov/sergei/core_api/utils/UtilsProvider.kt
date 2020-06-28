package com.kasianov.sergei.core_api.utils

interface UtilsProvider {
    fun provideCalcDateUtils(): CalcDateUtils
    fun provideGraphicUtils(): GraphicsUtils
}
