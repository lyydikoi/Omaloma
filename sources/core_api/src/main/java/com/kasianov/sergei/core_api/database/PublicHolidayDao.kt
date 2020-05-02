package com.kasianov.sergei.core_api.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.kasianov.sergei.core_api.model.dto.PublicHolidayDTO

@Dao
interface PublicHolidayDao {

    @Query("SELECT * FROM public_holiday_table WHERE year = :year AND countryCode = :country")
    fun getAllPublicHolidays(year: String, country: String): List<PublicHolidayDTO>

    @Query("SELECT * FROM public_holiday_table WHERE name = :name AND year = :year " +
            "AND countryCode = :country")
    fun getPublicHoliday(name: String, year: String, country: String): PublicHolidayDTO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPublicHolidays(holidaysList: List<PublicHolidayDTO>)

}