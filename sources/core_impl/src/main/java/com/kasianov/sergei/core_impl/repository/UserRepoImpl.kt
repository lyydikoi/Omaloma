package com.kasianov.sergei.core_impl.repository

import androidx.lifecycle.LiveData
import com.kasianov.sergei.core_api.model.dto.UserDTO
import com.kasianov.sergei.core_api.database.UserDao
import com.kasianov.sergei.core_api.repository.UserRepo
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