package com.kasianov.sergei.omaloma.ui.users

import androidx.lifecycle.ViewModel
import com.kasianov.sergei.omaloma.data.repositories.UserRepository
import com.kasianov.sergei.omaloma.data.source.local.daos.UserDao

class UserDetailsViewModel(
    val userRepo: UserRepository,
    val userDao: UserDao
) : ViewModel() {
    // TODO: Implement the ViewModel
}