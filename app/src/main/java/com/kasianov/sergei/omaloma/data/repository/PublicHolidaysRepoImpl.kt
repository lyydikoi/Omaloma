package com.kasianov.sergei.omaloma.data.repository

import androidx.lifecycle.Transformations
import com.kasianov.sergei.omaloma.core.extentions.*
import com.kasianov.sergei.omaloma.data.network.PublicHolidayApi
import com.kasianov.sergei.omaloma.data.database.dto.DBPublicHoliday
import com.kasianov.sergei.omaloma.data.database.PublicHolidayDao
import com.kasianov.sergei.omaloma.data.network.dto.PublicHolidayDTO
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo
import javax.inject.Inject

class PublicHolidaysRepoImpl @Inject constructor(
    private val pubHolidayApiService: PublicHolidayApi,
    private val publicHolidayDao: PublicHolidayDao,
    private val listMapperDTOToDBPublicHoliday: ListMapper<PublicHolidayDTO, DBPublicHoliday>,
    private val listMapperDTOToPublicHoliday: ListMapper<PublicHolidayDTO, PublicHoliday>,
    private val listMapperDBToPublicHoliday: ListMapper<DBPublicHoliday, PublicHoliday>,
    private val mapperDBToPublicHoliday: Mapper<DBPublicHoliday, PublicHoliday>
) : PublicHolidaysRepo {

    override suspend fun fetchRemotePublicHolidays(
        year: String,
        country: String
    ): RequestResult<List<PublicHoliday>> {
        val publicHolidaysResult =
            getRequestResult { pubHolidayApiService.getPublicHolidays(year, country) }

        return when (publicHolidaysResult) {
            is RequestResult.Success -> {
                val dbData = listMapperDTOToDBPublicHoliday.mapDto(publicHolidaysResult.data)
                val domainData = listMapperDTOToPublicHoliday.mapDto(publicHolidaysResult.data)

                saveAllPublicHolidays(dbData)
                RequestResult.Success(domainData)
            }
            is RequestResult.Error -> publicHolidaysResult
        }
    }

    private val _allPublicHolidays = publicHolidayDao.getAllPublicHolidays()
    override val allPublicHolidays = Transformations.map(_allPublicHolidays) {
        listMapperDBToPublicHoliday.mapDto(it)
    }

    override suspend fun getPublicHoliday(holidayName: String): PublicHoliday {
        val dbData = publicHolidayDao.getPublicHoliday(holidayName)
        return mapperDBToPublicHoliday.mapDto(dbData)

    }

    override suspend fun saveAllPublicHolidays(holidaysList: List<DBPublicHoliday>)
            = publicHolidayDao.insertAllPublicHolidays(holidaysList)

}