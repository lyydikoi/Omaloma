package com.kasianov.sergei.omaloma.domain.usecases

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo

// Interfaces
interface LoadPublicHolidaysUseCase {
    suspend operator fun invoke(year: String, country: String): RequestResult<List<PublicHoliday>>
}

interface GetAllStoredPublicHolidaysUseCase {
    operator fun invoke(): LiveData<List<PublicHoliday>>
}

interface GetStoredPublicHolidayUseCase {
    suspend operator fun invoke(name: String): PublicHoliday
}


// Implementation
class LoadPublicHolidaysUseCaseImpl(
    private val publicHolidayRepo: PublicHolidaysRepo
) : LoadPublicHolidaysUseCase {

    override suspend fun invoke(year: String, country: String): RequestResult<List<PublicHoliday>> {
        return publicHolidayRepo.fetchRemotePublicHolidays(year, country)
    }

}

class GetAllStoredPublicHolidaySUseCaseImpl(
    private val publicHolidayRepo: PublicHolidaysRepo
) : GetAllStoredPublicHolidaysUseCase {

    override fun invoke(): LiveData<List<PublicHoliday>> {
        return publicHolidayRepo.allPublicHolidays
    }

}

class GetStoredPublicHolidayUseCaseImpl(
    private val publicHolidayRepo: PublicHolidaysRepo
) : GetStoredPublicHolidayUseCase {

    override suspend fun invoke(name: String): PublicHoliday {
        return publicHolidayRepo.getPublicHoliday(name)
    }

}