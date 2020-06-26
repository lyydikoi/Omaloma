package com.kasianov.sergei.core_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kasianov.sergei.core_api.database.DataBaseContract
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO
import com.kasianov.sergei.core_api.model.dto.UserDTO
import com.kasianov.sergei.core_api.model.dto.PublicHolidayDTO

const val DATA_BASE_VERSION = 1
const val DATA_BASE_NAME = "omaloma.db"

@Database(
    entities = [
        UserDTO::class,
        AbsenceDTO::class,
        PublicHolidayDTO::class
    ],
    version = DATA_BASE_VERSION,
    exportSchema = false
)
abstract class OmaLomaDb : RoomDatabase(), DataBaseContract