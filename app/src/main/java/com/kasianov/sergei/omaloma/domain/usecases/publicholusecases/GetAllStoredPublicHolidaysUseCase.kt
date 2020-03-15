package com.kasianov.sergei.omaloma.domain.usecases.publicholusecases

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday

// Interfaces
interface GetAllStoredPublicHolidaysUseCase {
    operator fun invoke(): LiveData<List<PublicHoliday>>
}