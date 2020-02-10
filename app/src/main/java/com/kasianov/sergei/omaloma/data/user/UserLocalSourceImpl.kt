package com.kasianov.sergei.omaloma.data.user

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.data.user.local.DBUser
import com.kasianov.sergei.omaloma.data.user.local.UserDao
import com.kasianov.sergei.omaloma.data.user.local.DBUserWithAbsences

class UserLocalSourceImpl(private val userDao: UserDao) : UserDataContract.UserLocalSource {
    override fun getUser(id: String) = userDao.getUser(id)

    override fun getAllUsers(): LiveData<List<DBUser>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUsersWithAbsences(id: String): List<DBUserWithAbsences> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insert(user: DBUser) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun update(user: DBUser) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(user: DBUser) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}