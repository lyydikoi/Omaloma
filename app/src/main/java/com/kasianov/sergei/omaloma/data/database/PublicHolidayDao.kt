package com.kasianov.sergei.omaloma.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kasianov.sergei.omaloma.data.database.dto.DBPublicHoliday

@Dao
interface PublicHolidayDao {
    @Query("SELECT * FROM public_holiday_table")
    fun getAllPublicHolidays(): LiveData<List<DBPublicHoliday>>

    @Query("SELECT * FROM public_holiday_table WHERE localName = :name")
    fun getPublicHoliday(name: String): DBPublicHoliday

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPublicHolidays(holidaysList: List<DBPublicHoliday>)

}