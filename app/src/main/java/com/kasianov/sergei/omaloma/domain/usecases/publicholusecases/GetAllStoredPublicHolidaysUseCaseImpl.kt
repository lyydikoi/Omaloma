package com.kasianov.sergei.omaloma.domain.usecases.publicholusecases

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.domain.repository.PublicHolidaysRepo
import javax.inject.Inject

// Implementation
class GetAllStoredPublicHolidaysUseCaseImpl  @Inject constructor(
    private val publicHolidayRepo: PublicHolidaysRepo
) : GetAllStoredPublicHolidaysUseCase {

    override fun invoke(): LiveData<List<PublicHoliday>> {
        val data = publicHolidayRepo.allPublicHolidays.value
        return publicHolidayRepo.allPublicHolidays
    }

}