package com.kasianov.sergei.core_impl.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.kasianov.sergei.core_api.di_utils.PubHolRetrofitService
import com.kasianov.sergei.core_api.di_utils.WikiRetrofitService
import com.kasianov.sergei.core_api.network.PublicHolidayApi
import com.kasianov.sergei.core_api.network.WikiApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

const val PUBLIC_HOLIDAYS_BASE_URL = "https://date.nager.at/api/v2/"
const val WIKI_BASE_URL = "https://fi.wikipedia.org/w/"
const val TIMEOUT = 25L

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class WikiRetrofit

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class PubHolRetrofit

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun gsonBuilder(): GsonBuilder = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)

    // Wiki Retrofit service
    @Singleton
    @Provides
    @WikiRetrofit
    fun provideWikiRetrofit(okHttpClient: OkHttpClient, gsonBuilder: GsonBuilder): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WIKI_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(/*gsonBuilder.create()*/))
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

        /*if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(
                HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }*/

        return httpClient.build()
    }

}