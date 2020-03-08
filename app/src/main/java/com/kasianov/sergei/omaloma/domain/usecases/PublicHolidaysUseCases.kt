package com.kasianov.sergei.omaloma.domain.usecases

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo
import javax.inject.Inject

// Interfaces
interface GetAllStoredPublicHolidaysUseCase {
    operator fun invoke(): LiveData<List<PublicHoliday>>
}

interface GetStoredPublicHolidayUseCase {
    suspend operator fun invoke(name: String): PublicHoliday
}

interface LoadPublicHolidaysUseCase {
    suspend operator fun invoke(year: String, country: String): RequestResult<List<PublicHoliday>>
}

// Implementation
class GetAllStoredPublicHolidaysUseCaseImpl  @Inject constructor(
    private val publicHolidayRepo: PublicHolidaysRepo
) : GetAllStoredPublicHolidaysUseCase {

    override fun invoke(): LiveData<List<PublicHoliday>> {
        val data = publicHolidayRepo.allPublicHolidays.value
        return publicHolidayRepo.allPublicHolidays
    }

}

class GetStoredPublicHolidayUseCaseImpl  @Inject constructor(
    private val publicHolidayRepo: PublicHolidaysRepo
) : GetStoredPublicHolidayUseCase {

    override suspend fun invoke (name: String): PublicHoliday {
        return publicHolidayRepo.getPublicHoliday(name)
    }

}

class LoadPublicHolidaysUseCaseImpl @Inject constructor(
    private val publicHolidayRepo: PublicHolidaysRepo
) : LoadPublicHolidaysUseCase {

    override suspend fun invoke(year: String, country: String): RequestResult<List<PublicHoliday>> {
        return publicHolidayRepo.fetchRemotePublicHolidays(year, country)
    }

}