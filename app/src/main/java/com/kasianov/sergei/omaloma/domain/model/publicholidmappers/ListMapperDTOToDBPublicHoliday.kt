package com.kasianov.sergei.omaloma.domain.model.publicholidmappers

import com.kasianov.sergei.omaloma.core.extentions.ListMapperImpl
import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.database.dto.DBPublicHoliday
import com.kasianov.sergei.omaloma.data.network.dto.PublicHolidayDTO
import javax.inject.Inject

class ListMapperDTOToDBPublicHoliday @Inject constructor(
    mapper: Mapper<PublicHolidayDTO, DBPublicHoliday>
) : ListMapperImpl<PublicHolidayDTO, DBPublicHoliday>(mapper)