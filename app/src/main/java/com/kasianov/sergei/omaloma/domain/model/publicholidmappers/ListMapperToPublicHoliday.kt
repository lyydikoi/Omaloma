package com.kasianov.sergei.omaloma.domain.model.publicholidmappers

import com.kasianov.sergei.omaloma.core.extentions.ListMapperImpl
import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.model.PublicHolidayDTO
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import javax.inject.Inject

class ListMapperToPublicHoliday @Inject constructor(
    mapper: Mapper<PublicHolidayDTO, PublicHoliday>
) : ListMapperImpl<PublicHolidayDTO, PublicHoliday>(mapper)