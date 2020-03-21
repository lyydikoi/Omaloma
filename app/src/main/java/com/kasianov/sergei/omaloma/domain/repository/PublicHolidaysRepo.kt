package com.kasianov.sergei.omaloma.domain.repository

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.data.database.dto.DBPublicHoliday
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday

interface PublicHolidaysRepo {

    suspend fun fetchRemotePublicHolidays(
        year: String,
        country: String
    ): RequestResult<List<PublicHoliday>>

    val allPublicHolidays: LiveData<List<PublicHoliday>>

    suspend fun getPublicHoliday(name: String): PublicHoliday

    suspend fun saveAllPublicHolidays(holidaysList: List<DBPublicHoliday>)

}