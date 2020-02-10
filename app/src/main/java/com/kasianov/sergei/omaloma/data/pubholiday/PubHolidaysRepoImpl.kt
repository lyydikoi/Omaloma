package com.kasianov.sergei.omaloma.data.pubholiday

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.core.getRequestResult
import com.kasianov.sergei.omaloma.data.pubholiday.local.DBPublicHoliday
import com.kasianov.sergei.omaloma.data.pubholiday.local.PublicHolidayDao
import com.kasianov.sergei.omaloma.data.pubholiday.remote.*
import com.kasianov.sergei.omaloma.domain.PubHolidaysRepo

class PubHolidaysRepoImpl(
    private val pubHolidayApiService: PublicHolidayApi,
    private val publicHolidayDao: PublicHolidayDao
) : PubHolidaysRepo {

    override suspend fun getPublicHolidaysList(
        year: String,
        country: String
    ) : RequestResult<List<PublicHolidayDTO>> {
        return getRequestResult { pubHolidayApiService.getPublicHolidays(year, country) }
    }

    override fun getAllPublicHolidays(): LiveData<List<DBPublicHoliday>>
            = publicHolidayDao.getAllPublicHolidays()

    override fun getPublicHoliday(holidayId: String): LiveData<DBPublicHoliday>
            = publicHolidayDao.getPublicHoliday(holidayId)

    override suspend fun insertAllPucblicHolidays(holidaysList: List<DBPublicHoliday>)
            = publicHolidayDao.insertAllPublicHoidays(holidaysList)

}