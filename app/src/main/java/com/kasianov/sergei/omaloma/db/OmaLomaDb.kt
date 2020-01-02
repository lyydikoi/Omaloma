package com.kasianov.sergei.omaloma.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kasianov.sergei.omaloma.Utils.DATA_BASE_VERSION
import com.kasianov.sergei.omaloma.data.entities.Absence
import com.kasianov.sergei.omaloma.data.entities.User
import com.kasianov.sergei.omaloma.db.daos.UserDao

@Database(entities = [User::class, Absence::class], version = DATA_BASE_VERSION, exportSchema = false)
abstract class OmaLomaDb : RoomDatabase() {

    abstract fun userDao(): UserDao

}