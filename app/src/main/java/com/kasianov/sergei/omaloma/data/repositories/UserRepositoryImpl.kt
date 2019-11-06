package com.kasianov.sergei.omaloma.data.repositories

import com.kasianov.sergei.omaloma.data.Result
import com.kasianov.sergei.omaloma.data.entities.User

class UserRepositoryImpl : UserRepository {
    override suspend fun getUser(userId: String): Result<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllUsers(): Result<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}