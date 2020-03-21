package com.kasianov.sergei.omaloma.core.di

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
import javax.inject.Qualifier
import javax.inject.Singleton

const val PUBLIC_HOLIDAYS_BASE_URL = "https://date.nager.at/api/v2/"
const val WIKI_BASE_URL = "https://fi.wikipedia.org/w/"
const val WIKI_RETROFIT_SERVICE = "wiki_retrofit_service"

const val TIMEOUT = 25L

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class WikiRetrofit

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class WikiRetrofitService

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class PubHolRetrofit

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class PubHolRetrofitService

@Module
class NetworkModule {

    // Wiki Retrofit service
    @Singleton
    @Provides
    @WikiRetrofit
    fun provideWikiRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WIKI_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @WikiRetrofitService
    fun provideWikiRetrofitService (@WikiRetrofit retrofit: Retrofit): WikiApi {
        return retrofit.create(WikiApi::class.java)
    }

    // Public holidays Retrofit service
    @Singleton
    @Provides
    @PubHolRetrofit
    fun providePubHolRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(PUBLIC_HOLIDAYS_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @PubHolRetrofitService
    fun providePubHolRetrofitService(@PubHolRetrofit retrofit: Retrofit): PublicHolidayApi {
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
