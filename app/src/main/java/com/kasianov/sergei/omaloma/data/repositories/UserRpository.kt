package com.kasianov.sergei.omaloma.data.repositories

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.data.entities.User
import com.kasianov.sergei.omaloma.data.Result

interface UserRepository {

    fun getUser(userId: String): LiveData<User>

    fun getAllUsers(): LiveData<List<User>>

    suspend fun insert(user: User)

    suspend fun update(user: User)

    suspend fun delete(user: User)

}