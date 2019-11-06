package com.kasianov.sergei.omaloma.maincontent.data.source

import com.kasianov.sergei.omaloma.data.Result.Success
import com.kasianov.sergei.omaloma.data.Result.Error
import com.kasianov.sergei.omaloma.data.Result
import com.kasianov.sergei.omaloma.data.User
import com.kasianov.sergei.omaloma.data.source.UserRepository
import java.lang.Exception

class FakeUserRepository : UserRepository {

    var userServiceData: LinkedHashMap<String, User> = LinkedHashMap()

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) { shouldReturnError = value }

    override suspend fun getUser(userId: String) : Result<User> {
        Result.Loading
        if (shouldReturnError) {
            Error(Exception("Test exception"))
        }
        userServiceData[userId]?.let {
            return Success(it)
        }
        return Error(Exception("Could not find user"))
    }

    override suspend fun saveUser(user: User) {
        userServiceData[user.id] = user
    }
}