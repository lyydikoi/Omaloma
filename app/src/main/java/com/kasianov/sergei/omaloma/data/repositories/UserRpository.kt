package com.kasianov.sergei.omaloma.data.repositories

import com.kasianov.sergei.omaloma.data.entities.User
import com.kasianov.sergei.omaloma.data.Result

interface UserRepository {

    suspend fun getUser(userId: String): Result<User>

    suspend fun getAllUsers(): Result<List<User>>

    suspend fun saveUser(user: User)

}