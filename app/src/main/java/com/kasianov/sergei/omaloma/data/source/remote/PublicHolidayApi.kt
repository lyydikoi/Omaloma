package com.kasianov.sergei.omaloma.data.source.remote

import com.kasianov.sergei.omaloma.data.source.remote.dtos.PublicHolidayDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicHolidayApi {

    @GET("publicholidays/{year}/{country_code}")
    suspend fun getPublicHolidays(
        @Path("year") year: String,
        @Path("country_code") countryCode: String
    ): Response<List<PublicHolidayDto>>

}