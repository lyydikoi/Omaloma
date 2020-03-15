package com.kasianov.sergei.omaloma.domain.usecases.publicholusecases

import com.kasianov.sergei.omaloma.core.extentions.RequestResult
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday

interface LoadPublicHolidaysUseCase {
    suspend operator fun invoke(year: String, country: String): RequestResult<List<PublicHoliday>>
}