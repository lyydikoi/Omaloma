package com.kasianov.sergei.omaloma.core

import com.kasianov.sergei.omaloma.BuildConfig
import com.kasianov.sergei.omaloma.data.network.PublicHolidayApi
import com.kasianov.sergei.omaloma.data.network.WikiApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val PUBLIC_HOLIDAYS_BASE_URL = "https://date.nager.at/api/v2/"
const val WIKI_BASE_URL = "https://fi.wikipedia.org/w/"

// Utils for Public Holidays API service
fun providePubHolRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(PUBLIC_HOLIDAYS_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun providePubHolOkHttpClient(): OkHttpClient {
    val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient().newBuilder()
        .readTimeout(25, TimeUnit.SECONDS)
        .connectTimeout(25, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun providePubHolApi(retrofit: Retrofit): PublicHolidayApi =
    retrofit.create(PublicHolidayApi::class.java)


// Utils for Wiki Api Retrofit client
fun provideWikiRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(WIKI_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideWikiOkHttpClient(): OkHttpClient {
    val httpClient =  OkHttpClient().newBuilder()
        .readTimeout(25, TimeUnit.SECONDS)
        .connectTimeout(25, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        httpClient.addInterceptor(
            HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }

    return httpClient.build()
}

fun provideWikiApi(retrofit: Retrofit): WikiApi = retrofit.create(
    WikiApi::class.java)