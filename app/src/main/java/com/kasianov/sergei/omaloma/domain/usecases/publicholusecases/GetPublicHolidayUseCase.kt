package com.kasianov.sergei.omaloma.domain.usecases.publicholusecases

import com.kasianov.sergei.omaloma.domain.model.PublicHoliday

interface GetPublicHolidayUseCase {
    suspend operator fun invoke(name: String, year: String, country: String): PublicHoliday
}