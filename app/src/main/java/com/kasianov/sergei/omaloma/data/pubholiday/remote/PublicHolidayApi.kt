package com.kasianov.sergei.omaloma.data.pubholiday.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicHolidayApi {

    @GET("publicholidays/{year}/{country_code}")
    suspend fun getPublicHolidays(
        @Path("year") year: String,
        @Path("country_code") countryCode: String
    ): Response<List<PublicHolidayDTO>>

}