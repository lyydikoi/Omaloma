package com.kasianov.sergei.omaloma.maincontent.data.source

import androidx.lifecycle.LiveData
import com.kasianov.sergei.omaloma.data.model.UserDTO
import com.kasianov.sergei.omaloma.data.user.UserDataContract

class FakeUserRepository : UserDataContract.UserRepository {
    override fun getUser(userId: String): LiveData<UserDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllUsers(): LiveData<List<UserDTO>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insert(user: UserDTO) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun update(user: UserDTO) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(user: UserDTO) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val userServiceData by lazy { LinkedHashMap<String, UserDTO>() }
    private var shouldReturnError = false

    fun setReturnError(value: Boolean) { shouldReturnError = value }

    /*override suspend fun getUser(userId: String) : RequestResult<DBUser> {
        RequestResult.Loading

        if (shouldReturnError) {
            Error(Exception("Test exception"))
        }

        userServiceData[userId]?.let {
            return Success(it)
        }

        return Error(Exception("Could not find user"))
    }

    override suspend fun saveUser(user: DBUser) {
        userServiceData[user.id] = user
    }

    override suspend fun getAllUsers(): RequestResult<List<DBUser>> {
        RequestResult.Loading

        if (shouldReturnError) {
            Error(Exception("Test exception getAllUsers"))
        }

        val resultList = mutableListOf<DBUser>()
        userServiceData.forEach { (index, value) -> resultList.add(value) }

        return Success(resultList)
    }*/

}