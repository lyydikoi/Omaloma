package com.kasianov.sergei.omaloma.data.source

import com.kasianov.sergei.omaloma.data.User
import com.kasianov.sergei.omaloma.data.Result

interface UserRepository {

    suspend fun getUser(userId: String): Result<User>

    suspend fun saveUser(user: User)

}