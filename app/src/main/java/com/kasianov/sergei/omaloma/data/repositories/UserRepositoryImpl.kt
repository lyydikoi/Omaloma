package com.kasianov.sergei.omaloma.data.repositories

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.data.entities.User
import com.kasianov.sergei.omaloma.db.daos.UserDao

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override fun getUser(userId: String): LiveData<User> = userDao.getUser(userId)

    override fun getAllUsers(): LiveData<List<User>> = userDao.getAllUsers()

    override suspend fun insert(user: User) {
        userDao.insert(user)
    }

    override suspend fun update(user: User) {
        userDao.update(user)
    }

    override suspend fun delete(user: User) {
        userDao.delete(user)
    }

}