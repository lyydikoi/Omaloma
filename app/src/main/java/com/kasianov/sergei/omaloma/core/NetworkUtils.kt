package com.kasianov.sergei.omaloma.core

import com.kasianov.sergei.omaloma.BuildConfig
import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.data.pubholiday.remote.PublicHolidayApi
import com.kasianov.sergei.omaloma.data.wikiinfo.remote.WikiApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkUtils {

    // Utils for Public Holidays API service
    val pubHolApiService : PublicHolidayApi by lazy {
        return@lazy providePubHolApi(
            providePubHolRetrofit(
                providePubHolOkHttpClient()
            )
        )
    }

    private fun providePubHolRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(PUBLIC_HOLIDAYS_BASE_URL)
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

    private fun providePubHolApi(retrofit: Retrofit): PublicHolidayApi =
        retrofit.create(PublicHolidayApi::class.java)


    // Utils for Wiki Api Retrofit client
    val wikiApiService : WikiApi by lazy {
        return@lazy provideWikiApi(
            provideWikiRetrofit(provideWikiOkHttpClient())
        )
    }

    private fun provideWikiRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WIKI_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun provideWikiOkHttpClient(): OkHttpClient {
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

    private fun provideWikiApi(retrofit: Retrofit): WikiApi = retrofit.create(
        WikiApi::class.java)
}

/**
 * A generic method,which handles Retrofit2 response, wraps and returns [RequestResult]
 */
suspend fun <T> getRequestResult(call: suspend () -> Response<T>): RequestResult<T> {
    try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) return RequestResult.Success(body)
        }else if (response.errorBody() != null) {
            val errorBody = response.errorBody()
            return RequestResult.Error("error body:  ${errorBody?.string()}")
        }

        return RequestResult.Error("${response.code()} ${response.message()}")
    } catch (e: Exception) {
        return RequestResult.Error(e.message ?: e.toString())
    }
}