package com.kasianov.sergei.omaloma.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kasianov.sergei.omaloma.data.database.dto.DBAbsence

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