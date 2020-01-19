package com.kasianov.sergei.omaloma.data.source.remote

import com.kasianov.sergei.omaloma.Utils.PUBLIC_HOLIDAYS_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkUtils {

    val pubHolApiService : PublicHolidayApi by lazy {
        return@lazy providePubHolApi(providePubHolRetrofit(providePubHolOkHttpClient()))
    }

    private fun providePubHolRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(PUBLIC_HOLIDAYS_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun providePubHolOkHttpClient(): OkHttpClient {
        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient().newBuilder()
            .readTimeout(25, TimeUnit.SECONDS)
            .connectTimeout(25, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    private fun providePubHolApi(retrofit: Retrofit):PublicHolidayApi =
        retrofit.create(PublicHolidayApi::class.java)

}