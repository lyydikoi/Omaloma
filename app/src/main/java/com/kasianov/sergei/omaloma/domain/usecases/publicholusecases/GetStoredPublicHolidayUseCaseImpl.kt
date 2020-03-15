package com.kasianov.sergei.omaloma.domain.usecases.publicholusecases

import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo
import javax.inject.Inject

class GetStoredPublicHolidayUseCaseImpl  @Inject constructor(
    private val publicHolidayRepo: PublicHolidaysRepo
) : GetStoredPublicHolidayUseCase {

    override suspend fun invoke (name: String): PublicHoliday {
        return publicHolidayRepo.getPublicHoliday(name)
    }

}