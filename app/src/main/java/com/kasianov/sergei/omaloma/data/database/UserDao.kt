package com.kasianov.sergei.omaloma.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kasianov.sergei.omaloma.data.database.dto.DBUser
import com.kasianov.sergei.omaloma.data.database.dto.DBUserWithAbsences

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY lastName DESC")
    fun getAllUsers(): LiveData<List<DBUser>>

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUser(id: String): LiveData<DBUser>

    @Transaction
    @Query("SELECT * FROM user_table where id = :id")
    fun getUsersWithAbsences(id: String): List<DBUserWithAbsences>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: DBUser)

    @Update
    suspend fun update(user: DBUser)

    @Delete
    suspend fun delete(user: DBUser)
}