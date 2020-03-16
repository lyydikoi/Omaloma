package com.kasianov.sergei.omaloma.domain.repository

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.data.model.PublicHolidayDTO
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday

interface PublicHolidaysRepo {

    suspend fun getCachedOrRemotePublicHolidays(
        year: String,
        country: String
    ): RequestResult<List<PublicHoliday>>

    suspend fun getStoredPublicHoliday(name: String, year: String, country: String): PublicHoliday

    suspend fun saveAllPublicHolidays(holidaysList: List<PublicHolidayDTO>)

}