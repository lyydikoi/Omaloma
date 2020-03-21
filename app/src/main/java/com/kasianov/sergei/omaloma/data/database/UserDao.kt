package com.kasianov.sergei.omaloma.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kasianov.sergei.omaloma.data.model.UserDTO
import com.kasianov.sergei.omaloma.data.model.UserWithAbsencesDTO

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