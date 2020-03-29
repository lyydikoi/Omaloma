package com.kasianov.sergei.core_api.di_utils

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class WikiRetrofitService

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class PubHolRetrofitService