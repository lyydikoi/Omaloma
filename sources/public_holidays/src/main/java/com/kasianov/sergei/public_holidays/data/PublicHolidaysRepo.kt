package com.kasianov.sergei.public_holidays.data

import com.kasianov.sergei.core_api.extentions.RequestResult
import com.kasianov.sergei.core_api.model.dto.PublicHolidayDTO

interface PublicHolidaysRepo {

    suspend fun getStoredOrRemotePublicHolidays(
        year: String,
        country: String
    ): RequestResult<List<PublicHolidayDTO>>

    suspend fun getStoredPublicHoliday(name: String, year: String, country: String): PublicHolidayDTO

    suspend fun saveAllPublicHolidays(holidaysList: List<PublicHolidayDTO>)
}
