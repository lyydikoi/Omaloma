package com.kasianov.sergei.omaloma.domain.model.mappers

import com.kasianov.sergei.omaloma.core.extentions.ListMapper
import com.kasianov.sergei.omaloma.core.extentions.ListMapperImpl
import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.database.dto.DBPublicHoliday
import com.kasianov.sergei.omaloma.data.network.dto.PublicHolidayDTO
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import com.kasianov.sergei.omaloma.presentation.utils.CalcDatesUtils
import javax.inject.Inject

class MapperDTOToPublicHoliday : Mapper<PublicHolidayDTO, PublicHoliday> {
    override fun mapDto(input: PublicHolidayDTO): PublicHoliday {
        return PublicHoliday(
            input.name?.let { it } ?: "",
            input.localName?.let { it } ?: "",
            input.date?.let { CalcDatesUtils.getFormattedDate(it) } ?: "",
            input.countryCode?.let { it } ?: ""
        )
    }
}

class ListMapperDTOToPublicHoliday @Inject constructor(
    mapper: Mapper<PublicHolidayDTO, PublicHoliday>
) : ListMapperImpl<PublicHolidayDTO, PublicHoliday>(mapper)

class MapperDTOToDBPublicHoliday : Mapper<PublicHolidayDTO, DBPublicHoliday> {
    override fun mapDto(input: PublicHolidayDTO): DBPublicHoliday {
        return DBPublicHoliday(
            input.name?.let { it } ?: "",
            input.localName?.let { it } ?: "",
            input.date?.let { CalcDatesUtils.getFormattedDate(it) } ?: "",
            input.countryCode?.let { it } ?: ""
        )
    }
}

class ListMapperDTOToDBPublicHoliday @Inject constructor(
    mapper: Mapper<PublicHolidayDTO, DBPublicHoliday>
) : ListMapperImpl<PublicHolidayDTO, DBPublicHoliday>(mapper)

class MapperDBToPublicHoliday : Mapper<DBPublicHoliday, PublicHoliday> {
    override fun mapDto(input: DBPublicHoliday): PublicHoliday {
        return PublicHoliday(
            input.name,
            input.localName,
            input.dateFormatted,
            input.countryCode
        )
    }
}

class ListMapperDBToPublicHoliday @Inject constructor(
    mapper: Mapper<DBPublicHoliday, PublicHoliday>
) : ListMapperImpl<DBPublicHoliday, PublicHoliday>(mapper)