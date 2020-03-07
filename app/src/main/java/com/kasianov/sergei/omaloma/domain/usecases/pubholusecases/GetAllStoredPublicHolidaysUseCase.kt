package com.kasianov.sergei.omaloma.domain.usecases.pubholusecases

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday

interface GetAllStoredPublicHolidaysUseCase {
    operator fun invoke(): LiveData<List<PublicHoliday>>
}