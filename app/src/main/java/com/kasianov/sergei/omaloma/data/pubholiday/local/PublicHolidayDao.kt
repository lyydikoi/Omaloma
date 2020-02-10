package com.kasianov.sergei.omaloma.data.pubholiday.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PublicHolidayDao {
    @Query("SELECT * FROM public_holiday_table ORDER BY dateMilliseconds DESC")
    fun getAllPublicHolidays(): LiveData<List<DBPublicHoliday>>

    @Query("SELECT * FROM public_holiday_table WHERE id = :id")
    fun getPublicHoliday(id: String): LiveData<DBPublicHoliday>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPublicHoidays(holidaysList: List<DBPublicHoliday>)

}