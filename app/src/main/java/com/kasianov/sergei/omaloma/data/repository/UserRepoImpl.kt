package com.kasianov.sergei.omaloma.data.repository

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.data.model.UserDTO
import com.kasianov.sergei.omaloma.data.database.UserDao
import com.kasianov.sergei.omaloma.domain.repository.UserRepo
import javax.inject.Inject

class UserRepoImpl @Inject constructor(private val userDao: UserDao) : UserRepo {

    override fun getUser(userId: String): LiveData<UserDTO> = userDao.getUser(userId)

    override fun getAllUsers(): LiveData<List<UserDTO>> = userDao.getAllUsers()

    override suspend fun saveUser(user: UserDTO) {
        userDao.insert(user)
    }

    override suspend fun updateUser(user: UserDTO) {
        userDao.update(user)
    }

    override suspend fun deleteUser(user: UserDTO) {
        userDao.delete(user)
    }

}