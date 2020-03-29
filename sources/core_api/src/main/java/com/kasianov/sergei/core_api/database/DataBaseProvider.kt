package com.kasianov.sergei.core_api.database

import com.kasianov.sergei.core_api.database.AbsenceDao
import com.kasianov.sergei.core_api.database.DataBaseContract
import com.kasianov.sergei.core_api.database.PublicHolidayDao
import com.kasianov.sergei.core_api.database.UserDao

interface DataBaseProvider {

    fun provideOmalomaDatabase() : DataBaseContract

    fun userDao() : UserDao

    fun publicHolidayDao() : PublicHolidayDao

    fun absenceDao() : AbsenceDao

}