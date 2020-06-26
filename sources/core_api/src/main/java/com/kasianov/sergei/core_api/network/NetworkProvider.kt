package com.kasianov.sergei.core_api.network

import com.kasianov.sergei.core_api.di_utils.PubHolRetrofitService
import com.kasianov.sergei.core_api.di_utils.WikiRetrofitService

interface NetworkProvider {

    @WikiRetrofitService
    fun provideWikiService() :  WikiApi

    @PubHolRetrofitService
    fun providePublicHolidayService() : PublicHolidayApi

}
