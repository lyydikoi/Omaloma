package com.kasianov.sergei.omaloma.domain.model.publicholidmappers

import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.model.PublicHolidayDTO
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.presentation.utils.CalcDatesUtils
import javax.inject.Inject

class MapperDTOToPublicHoliday @Inject constructor() :
    Mapper<PublicHolidayDTO, PublicHoliday> {
    override fun mapDto(input: PublicHolidayDTO): PublicHoliday {
        return PublicHoliday(
            input.name?.let { it } ?: "",
            input.localName?.let { it } ?: "",
            input.date?.let {
                CalcDatesUtils.getFormattedDate(
                    it
                )
            } ?: "",
            input.countryCode?.let { it } ?: ""
        )
    }
}