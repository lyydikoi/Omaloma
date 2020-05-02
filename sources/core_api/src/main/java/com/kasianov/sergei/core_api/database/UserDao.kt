package com.kasianov.sergei.core_api.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import com.kasianov.sergei.core_api.model.dto.UserDTO
import com.kasianov.sergei.core_api.model.dto.UserWithAbsencesDTO

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY lastName DESC")
    fun getAllUsers(): LiveData<List<UserDTO>>

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUser(id: String): LiveData<UserDTO>

    @Transaction
    @Query("SELECT * FROM user_table where id = :id")
    fun getUsersWithAbsences(id: String): List<UserWithAbsencesDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserDTO)

    @Update
    suspend fun update(user: UserDTO)

    @Delete
    suspend fun delete(user: UserDTO)

}