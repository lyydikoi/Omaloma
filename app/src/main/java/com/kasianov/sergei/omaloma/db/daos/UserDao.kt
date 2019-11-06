package com.kasianov.sergei.omaloma.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kasianov.sergei.omaloma.data.entities.User

@Dao
interface UserDao {
    @Query("SELECT * from user_table ORDER BY lastName DESC")
    fun getAllUsersUI(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}