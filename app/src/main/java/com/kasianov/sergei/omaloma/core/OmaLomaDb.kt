package com.kasianov.sergei.omaloma.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kasianov.sergei.omaloma.data.database.AbsenceDao
import com.kasianov.sergei.omaloma.data.database.PublicHolidayDao
import com.kasianov.sergei.omaloma.data.database.dto.DBAbsence
import com.kasianov.sergei.omaloma.data.database.dto.DBUser
import com.kasianov.sergei.omaloma.data.database.UserDao

const val DATA_BASE_VERSION = 1
const val DATA_BASE_NAME = "omaloma.db"

@Database(
    entities = [
        DBUser::class,
        DBAbsence::class,
        DBPublicHoliday::class
    ],
    version = DATA_BASE_VERSION,
    exportSchema = false
)
abstract class OmaLomaDb : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun publicHolidayDao(): PublicHolidayDao
    abstract fun absenceDao(): AbsenceDao

}