package com.kasianov.sergei.omaloma.domain.model.publicholidmappers

import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.model.PublicHolidayDTO
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.presentation.utils.CalcDateUtils
import javax.inject.Inject

class MapperToPublicHoliday @Inject constructor(
    private val calcDateUtils: CalcDateUtils
) : Mapper<PublicHolidayDTO, PublicHoliday> {

    override fun mapDto(input: PublicHolidayDTO): PublicHoliday {
        return PublicHoliday(
            input.name ?: "",
            input.localName ?: "",
            input.date?.let { calcDateUtils.getFormattedDate(it) } ?: "",
            input.countryCode ?: ""
        )
    }

}