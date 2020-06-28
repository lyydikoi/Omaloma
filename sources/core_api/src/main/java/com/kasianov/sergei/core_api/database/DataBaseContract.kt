package com.kasianov.sergei.core_api.database

interface DataBaseContract {

    abstract fun userDao(): UserDao

    abstract fun publicHolidayDao(): PublicHolidayDao

    abstract fun absenceDao(): AbsenceDao
}
