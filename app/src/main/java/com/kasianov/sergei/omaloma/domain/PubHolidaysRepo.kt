package com.kasianov.sergei.omaloma.domain

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.data.pubholiday.local.DBPublicHoliday
import com.kasianov.sergei.omaloma.data.pubholiday.remote.PublicHolidayDTO
import com.kasianov.sergei.omaloma.data.user.local.DBUser

interface PubHolidaysRepo {
    suspend fun getPublicHolidaysList(year: String, country: String): RequestResult<List<PublicHolidayDTO>>

    fun getAllPublicHolidays(): LiveData<List<DBPublicHoliday>>

    fun getPublicHoliday(holidayId: String): LiveData<DBPublicHoliday>

    suspend fun insertAllPucblicHolidays(holidaysList: List<DBPublicHoliday>)

}