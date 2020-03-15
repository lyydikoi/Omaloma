package com.kasianov.sergei.omaloma.domain.model.publicholidmappers

import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.database.dto.DBPublicHoliday
import com.kasianov.sergei.omaloma.data.network.dto.PublicHolidayDTO
import com.kasianov.sergei.omaloma.presentation.utils.CalcDatesUtils
import javax.inject.Inject

class MapperDTOToDBPublicHoliday @Inject constructor() :
    Mapper<PublicHolidayDTO, DBPublicHoliday> {
    override fun mapDto(input: PublicHolidayDTO): DBPublicHoliday {
        return DBPublicHoliday(
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