package com.kasianov.sergei.omaloma.data.source.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kasianov.sergei.omaloma.data.entities.User
import com.kasianov.sergei.omaloma.data.entities.UserWithAbsences

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY lastName DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUser(id: String): LiveData<User>

    @Transaction
    @Query("SELECT * FROM user_table where id = :id")
    fun getUsersWithAbsences(id: String): List<UserWithAbsences>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}