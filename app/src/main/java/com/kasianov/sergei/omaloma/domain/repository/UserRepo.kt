package com.kasianov.sergei.omaloma.domain.repository

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.data.database.dto.DBUser

interface UserRepo {

    fun getUser(userId: String): LiveData<DBUser>

    fun getAllUsers(): LiveData<List<DBUser>>

    suspend fun saveUser(user: DBUser)

    suspend fun updateUser(user: DBUser)

    suspend fun deleteUser(user: DBUser)

}