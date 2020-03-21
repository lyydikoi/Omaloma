package com.kasianov.sergei.omaloma.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kasianov.sergei.omaloma.data.model.AbsenceDTO

@Dao
interface AbsenceDao {
    @Query("SELECT * FROM absence_table WHERE created_at = :createdAt")
    fun getAbsene(createdAt: String): LiveData<AbsenceDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbsence(absence: AbsenceDTO)

    @Update
    suspend fun updateAbsence(absence: AbsenceDTO)

    @Delete
    suspend fun deleteAbsence(absence: AbsenceDTO)

}