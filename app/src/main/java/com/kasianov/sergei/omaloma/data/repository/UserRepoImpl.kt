package com.kasianov.sergei.omaloma.data.repository

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.data.model.UserDTO
import com.kasianov.sergei.omaloma.data.database.UserDao
import com.kasianov.sergei.omaloma.domain.repository.UserRepo
import javax.inject.Inject

class UserRepoImpl @Inject constructor(private val userDao: UserDao) : UserRepo {

    // TODO: use mapper to return Domain models instead of DTOs
    override fun getUser(userId: String): LiveData<UserDTO> = userDao.getUser(userId)

    // TODO: use mapper to return Domain models instead of DTOs
    override fun getAllUsers(): LiveData<List<UserDTO>> = userDao.getAllUsers()

    // TODO: use mapper to return Domain models instead of DTOs
    override suspend fun saveUser(user: UserDTO) {
        userDao.insert(user)
    }

    // TODO: use mapper to return Domain models instead of DTOs
    override suspend fun updateUser(user: UserDTO) {
        userDao.update(user)
    }

    // TODO: use mapper to return Domain models instead of DTOs
    override suspend fun deleteUser(user: UserDTO) {
        userDao.delete(user)
    }

}