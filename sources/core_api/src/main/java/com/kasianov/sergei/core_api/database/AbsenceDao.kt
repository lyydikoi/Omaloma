package com.kasianov.sergei.core_api.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kasianov.sergei.core_api.model.dto.AbsenceDTO

@Dao
interface AbsenceDao {
    @Query("SELECT * FROM absence_table")
    fun getAllAbsences(): LiveData<List<AbsenceDTO>>

    @Query("SELECT * FROM absence_table WHERE created_at = :createdMillis")
    fun getAbsence(createdMillis: Long): AbsenceDTO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbsence(absence: AbsenceDTO)

    @Update
    suspend fun updateAbsence(absence: AbsenceDTO)

    @Delete
    suspend fun deleteAbsence(absence: AbsenceDTO)
}
