package com.kasianov.sergei.omaloma.data.absence.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AbsenceDao {
    @Query("SELECT * FROM absence_table WHERE created_at = :createdAt")
    fun getAbsene(createdAt: String): LiveData<DBAbsence>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbsence(absence: DBAbsence)

    @Update
    suspend fun updateAbsence(absence: DBAbsence)

    @Delete
    suspend fun deleteAbsence(absence: DBAbsence)

}