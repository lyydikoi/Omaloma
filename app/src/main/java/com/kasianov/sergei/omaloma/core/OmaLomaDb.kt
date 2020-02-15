package com.kasianov.sergei.omaloma.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kasianov.sergei.omaloma.data.absence.local.DBAbsence
import com.kasianov.sergei.omaloma.data.user.local.DBUser
import com.kasianov.sergei.omaloma.data.user.local.UserDao

const val DATA_BASE_VERSION = 1
const val DATA_BASE_NAME = "omaloma.db"

@Database(entities = [DBUser::class, DBAbsence::class], version = DATA_BASE_VERSION, exportSchema = false)
abstract class OmaLomaDb : RoomDatabase() {

    abstract fun userDao(): UserDao

}