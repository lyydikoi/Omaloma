package com.kasianov.sergei.omaloma.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kasianov.sergei.omaloma.data.entities.Absence

@Dao
interface AbsenceDao {
    @Query("SELECT * FROM absence_table WHERE created_at = :createdAt")
    fun getAbsene(createdAt: String): LiveData<Absence>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(absence: Absence)

    @Update
    suspend fun update(absence: Absence)

    @Delete
    suspend fun delete(absence: Absence)

}