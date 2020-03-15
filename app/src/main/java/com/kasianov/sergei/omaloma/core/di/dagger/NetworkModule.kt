package com.kasianov.sergei.omaloma.core.di.dagger

import com.kasianov.sergei.omaloma.BuildConfig
import com.kasianov.sergei.omaloma.data.network.PublicHolidayApi
import com.kasianov.sergei.omaloma.data.network.WikiApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

const val PUBLIC_HOLIDAYS_BASE_URL = "https://date.nager.at/api/v2/"
const val WIKI_BASE_URL = "https://fi.wikipedia.org/w/"
const val PUB_HOL_RETROFIT = "pub_hol_retrofit"
const val PUB_HOL_RETROFIT_SERVICE = "pub_hol_retrofit_service"
const val WIKI_RETROFIT = "wiki_retrofit"
const val WIKI_RETROFIT_SERVICE = "wiki_retrofit_service"

const val TIMEOUT = 25L

@Module
class NetworkModule {

    // Wiki Retrofit service
    @Singleton
    @Provides
    @Named(WIKI_RETROFIT)
    fun provideWikiRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WIKI_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named(WIKI_RETROFIT_SERVICE)
    fun provideWikiRetrofitService (@Named(WIKI_RETROFIT) retrofit: Retrofit): WikiApi {
        return retrofit.create(WikiApi::class.java)
    }

    // Public holidays Retrofit service
    @Singleton
    @Provides
    @Named(PUB_HOL_RETROFIT)
    fun providePubHolRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(PUBLIC_HOLIDAYS_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named(PUB_HOL_RETROFIT_SERVICE)
    fun providePubHolRetrofitService(@Named(PUB_HOL_RETROFIT) retrofit: Retrofit): PublicHolidayApi {
        return retrofit.create(PublicHolidayApi::class.java)
    }

    // OkHttpclient
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient =  OkHttpClient().newBuilder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(
                HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }

        return httpClient.build()
    }

}
