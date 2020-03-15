package com.kasianov.sergei.omaloma.domain.usecases.publicholusecases

import com.kasianov.sergei.omaloma.domain.model.PublicHoliday

interface GetStoredPublicHolidayUseCase {
    suspend operator fun invoke(name: String): PublicHoliday
}