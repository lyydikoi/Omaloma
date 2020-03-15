package com.kasianov.sergei.omaloma.domain.model.publicholidmappers

import com.kasianov.sergei.omaloma.core.extentions.ListMapperImpl
import com.kasianov.sergei.omaloma.core.extentions.Mapper
import com.kasianov.sergei.omaloma.data.database.dto.DBPublicHoliday
import com.kasianov.sergei.omaloma.domain.model.PublicHoliday
import javax.inject.Inject

class ListMapperDBToPublicHoliday @Inject constructor(
    mapper: Mapper<DBPublicHoliday, PublicHoliday>
) : ListMapperImpl<DBPublicHoliday, PublicHoliday>(mapper)