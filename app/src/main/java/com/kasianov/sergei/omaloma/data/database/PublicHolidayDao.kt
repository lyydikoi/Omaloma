package com.kasianov.sergei.omaloma.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kasianov.sergei.omaloma.data.model.PublicHolidayDTO

@Dao
interface PublicHolidayDao {
    @Query("SELECT * FROM public_holiday_table WHERE launchYear = :year AND countryCode = :country")
    fun getAllPublicHolidays(year: String, country: String): List<PublicHolidayDTO>

    @Query("SELECT * FROM public_holiday_table WHERE localName = :name AND launchYear = :year " +
            "AND countryCode = :country")
    fun getPublicHoliday(name: String, year: String, country: String): PublicHolidayDTO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPublicHolidays(holidaysList: List<PublicHolidayDTO>)

}