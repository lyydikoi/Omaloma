package com.kasianov.sergei.core_api.repository

import androidx.lifecycle.LiveData
import com.kasianov.sergei.core_api.model.dto.UserDTO

interface UserRepo {

    fun getUser(userId: String): LiveData<UserDTO>

    fun getAllUsers(): LiveData<List<UserDTO>>

    suspend fun saveUser(user: UserDTO)

    suspend fun updateUser(user: UserDTO)

    suspend fun deleteUser(user: UserDTO)

}