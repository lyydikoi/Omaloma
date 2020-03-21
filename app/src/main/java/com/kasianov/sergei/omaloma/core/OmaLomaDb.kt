package com.kasianov.sergei.omaloma.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kasianov.sergei.omaloma.data.database.AbsenceDao
import com.kasianov.sergei.omaloma.data.database.PublicHolidayDao
import com.kasianov.sergei.omaloma.data.model.AbsenceDTO
import com.kasianov.sergei.omaloma.data.model.UserDTO
import com.kasianov.sergei.omaloma.data.database.UserDao
import com.kasianov.sergei.omaloma.data.model.PublicHolidayDTO

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
abstract class OmaLomaDb : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun publicHolidayDao(): PublicHolidayDao
    abstract fun absenceDao(): AbsenceDao

}