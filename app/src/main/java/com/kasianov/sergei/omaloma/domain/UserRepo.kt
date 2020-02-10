package com.kasianov.sergei.omaloma.domain

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.data.user.local.DBUser

interface UserRepo {
    fun getUser(userId: String): LiveData<DBUser>

    fun getAllUsers(): LiveData<List<DBUser>>

    suspend fun insert(user: DBUser)

    suspend fun update(user: DBUser)

    suspend fun delete(user: DBUser)

}