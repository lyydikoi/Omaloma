package com.kasianov.sergei.omaloma.data.repository

import com.kasianov.sergei.omaloma.core.di.dagger.PUB_HOL_RETROFIT_SERVICE
import com.kasianov.sergei.omaloma.core.extentions.ListMapper
import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.core.extentions.getRequestResult
import com.kasianov.sergei.omaloma.data.network.PublicHolidayApi
import com.kasianov.sergei.omaloma.data.database.PublicHolidayDao
import com.kasianov.sergei.omaloma.data.model.PublicHolidayDTO
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo
import javax.inject.Inject
import javax.inject.Named

class PublicHolidaysRepoImpl @Inject constructor(
    @Named(PUB_HOL_RETROFIT_SERVICE) private val pubHolidayApiService: PublicHolidayApi,
    private val publicHolidayDao: PublicHolidayDao,
    private val mapperToPublicHoliday: Mapper<PublicHolidayDTO, PublicHoliday>,
    private val listMapperToPublicHoliday: ListMapper<PublicHolidayDTO, PublicHoliday>
) : PublicHolidaysRepo {

    override suspend fun getCachedOrRemotePublicHolidays(
        year: String,
        country: String
    ): RequestResult<List<PublicHoliday>> {
        val dbData = publicHolidayDao.getAllPublicHolidays(year, country)

        return if (dbData.isNotEmpty()) {
            RequestResult.Success(listMapperToPublicHoliday.mapDto(dbData))
        } else {
            val publicHolidaysResult =
                getRequestResult { pubHolidayApiService.getPublicHolidays(year, country) }

            when (publicHolidaysResult) {
                is RequestResult.Success -> {
                    saveAllPublicHolidays(publicHolidaysResult.data)
                    RequestResult.Success(listMapperToPublicHoliday.mapDto(publicHolidaysResult.data))
                }
                is RequestResult.Error -> publicHolidaysResult
            }
        }
    }

    override suspend fun getStoredPublicHoliday(name: String, year: String, country: String): PublicHoliday {
        val dbData = publicHolidayDao.getPublicHoliday(name, year, country)
        return mapperToPublicHoliday.mapDto(dbData)
    }

    override suspend fun saveAllPublicHolidays(holidaysList: List<PublicHolidayDTO>)
            = publicHolidayDao.insertAllPublicHolidays(holidaysList)

}