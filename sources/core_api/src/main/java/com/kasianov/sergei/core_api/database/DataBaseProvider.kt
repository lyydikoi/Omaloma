package com.kasianov.sergei.core_api.database

interface DataBaseProvider {

    fun provideOmalomaDatabase() : DataBaseContract

    fun userDao() : UserDao

    fun publicHolidayDao() : PublicHolidayDao

    fun absenceDao() : AbsenceDao

}