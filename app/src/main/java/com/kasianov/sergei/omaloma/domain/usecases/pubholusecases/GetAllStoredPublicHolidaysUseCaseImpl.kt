package com.kasianov.sergei.omaloma.domain.usecases.pubholusecases

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo
import com.kasianov.sergei.omaloma.domain.usecases.pubholusecases.GetAllStoredPublicHolidaysUseCase
import javax.inject.Inject

class GetAllStoredPublicHolidaysUseCaseImpl  @Inject constructor(
    private val publicHolidayRepo: PublicHolidaysRepo
) : GetAllStoredPublicHolidaysUseCase {

    override fun invoke(): LiveData<List<PublicHoliday>> {
        return publicHolidayRepo.allPublicHolidays
    }

}