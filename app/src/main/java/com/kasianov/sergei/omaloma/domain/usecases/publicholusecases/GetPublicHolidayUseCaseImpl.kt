package com.kasianov.sergei.omaloma.domain.usecases.publicholusecases

import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo
import javax.inject.Inject

class GetPublicHolidayUseCaseImpl  @Inject constructor(
    private val publicHolidayRepo: PublicHolidaysRepo
) : GetPublicHolidayUseCase {

    override suspend fun invoke (name: String, year: String, country: String): PublicHoliday {
        return publicHolidayRepo.getStoredPublicHoliday(name, year, country)
    }

}