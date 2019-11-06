package com.kasianov.sergei.omaloma.maincontent.data.source

import com.kasianov.sergei.omaloma.data.Result.Success
import com.kasianov.sergei.omaloma.data.Result.Error
import com.kasianov.sergei.omaloma.data.Result
import com.kasianov.sergei.omaloma.data.entities.User
import com.kasianov.sergei.omaloma.data.repositories.UserRepository
import java.lang.Exception

class FakeUserRepository : UserRepository {
    private val userServiceData by lazy { LinkedHashMap<String, User>() }
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

    override suspend fun getAllUsers(): Result<List<User>> {
        Result.Loading

        if (shouldReturnError) {
            Error(Exception("Test exception getAllUsers"))
        }

        val resultList = mutableListOf<User>()
        userServiceData.forEach { (index, value) -> resultList.add(value) }

        return Success(resultList)
    }

}