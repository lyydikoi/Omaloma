package com.kasianov.sergei.omaloma.domain.model.publicholidmappers

import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import javax.inject.Inject

class MapperDBToPublicHoliday @Inject constructor() :
    Mapper<DBPublicHoliday, PublicHoliday> {
    override fun mapDto(input: DBPublicHoliday): PublicHoliday {
        return PublicHoliday(
            input.name,
            input.localName,
            input.dateFormatted,
            input.countryCode
        )
    }
}