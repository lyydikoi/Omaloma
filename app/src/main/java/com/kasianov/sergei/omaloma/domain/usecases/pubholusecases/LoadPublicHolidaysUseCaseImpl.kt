package com.kasianov.sergei.omaloma.domain.usecases.pubholusecases

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo
import javax.inject.Inject

class LoadPublicHolidaysUseCaseImpl @Inject constructor(
    private val publicHolidayRepo: PublicHolidaysRepo
) : LoadPublicHolidaysUseCase {

    override suspend fun invoke(year: String, country: String): RequestResult<List<PublicHoliday>> {
        return publicHolidayRepo.fetchRemotePublicHolidays(year, country)
    }

}