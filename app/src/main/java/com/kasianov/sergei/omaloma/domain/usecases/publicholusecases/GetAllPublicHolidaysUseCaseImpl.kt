package com.kasianov.sergei.omaloma.domain.usecases.publicholusecases

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo
import javax.inject.Inject

class GetAllPublicHolidaysUseCaseImpl @Inject constructor(
    private val publicHolidayRepo: PublicHolidaysRepo
) : GetAllPublicHolidaysUseCase {

    override suspend fun invoke(year: String, country: String): RequestResult<List<PublicHoliday>> {
        return publicHolidayRepo.getStoredOrRemotePublicHolidays(year, country)
    }

}