package com.kasianov.sergei.public_holidays.data

import com.kasianov.sergei.core_api.database.PublicHolidayDao
import com.kasianov.sergei.core_api.di_utils.PubHolRetrofitService
import com.kasianov.sergei.core_api.model.dto.PublicHolidayDTO
import com.kasianov.sergei.core_api.extentions.RequestResult
import com.kasianov.sergei.core_api.extentions.getRequestResult
import com.kasianov.sergei.core_api.network.PublicHolidayApi
import javax.inject.Inject

class PublicHolidaysRepoImpl @Inject constructor(
    @PubHolRetrofitService private val pubHolidayApiService: PublicHolidayApi,
    private val publicHolidayDao: PublicHolidayDao
) : PublicHolidaysRepo {

    override suspend fun getStoredOrRemotePublicHolidays(
        year: String,
        country: String
    ): RequestResult<List<PublicHolidayDTO>> {
        val dbData = publicHolidayDao.getAllPublicHolidays(year, country)

        return if (dbData.isNotEmpty()) {
            RequestResult.Success(dbData)
        } else {
            when (val publicHolidaysResult =
                getRequestResult { pubHolidayApiService.getPublicHolidays(year, country) }) {

                is RequestResult.Success -> {
                    saveHolidaysWithYear(year, publicHolidaysResult.data)
                    RequestResult.Success(publicHolidaysResult.data)
                }
                is RequestResult.Error ->
                    publicHolidaysResult
            }
        }
    }

    private suspend fun saveHolidaysWithYear(year: String, holidaysList: List<PublicHolidayDTO>) {
        holidaysList.map { holiday: PublicHolidayDTO -> holiday.year = year }
        saveAllPublicHolidays(holidaysList)
    }

    override suspend fun getStoredPublicHoliday(name: String, year: String, country: String): PublicHolidayDTO {
        return publicHolidayDao.getPublicHoliday(name, year, country)
    }

    override suspend fun saveAllPublicHolidays(holidaysList: List<PublicHolidayDTO>) =
        publicHolidayDao.insertAllPublicHolidays(holidaysList)

}