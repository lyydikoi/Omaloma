package com.kasianov.sergei.omaloma.data.user

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.data.user.local.DBUser
import com.kasianov.sergei.omaloma.data.user.local.UserDao
import com.kasianov.sergei.omaloma.domain.UserRepo

class UserRepoImpl(private val userDao: UserDao) : UserRepo {

    override fun getUser(userId: String): LiveData<DBUser> = userDao.getUser(userId)

    override fun getAllUsers(): LiveData<List<DBUser>> = userDao.getAllUsers()

    override suspend fun insert(user: DBUser) {
        userDao.insert(user)
    }

    override suspend fun update(user: DBUser) {
        userDao.update(user)
    }

    override suspend fun delete(user: DBUser) {
        userDao.delete(user)
    }

}