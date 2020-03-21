package com.kasianov.sergei.omaloma.core.di

import android.content.Context
import androidx.room.Room
import com.kasianov.sergei.omaloma.core.DATA_BASE_NAME
import com.kasianov.sergei.omaloma.core.OmaLomaDb
import com.kasianov.sergei.omaloma.data.database.AbsenceDao
import com.kasianov.sergei.omaloma.data.database.PublicHolidayDao
import com.kasianov.sergei.omaloma.data.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Context): OmaLomaDb {
        return Room
            .databaseBuilder(context.applicationContext, OmaLomaDb::class.java, DATA_BASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Reusable
    fun provideUserDao(omaLomaDb: OmaLomaDb): UserDao {
        return omaLomaDb.userDao()
    }

    @Provides
    @Reusable
    fun providePublicHolidayDao(omaLomaDb: OmaLomaDb): PublicHolidayDao {
        return omaLomaDb.publicHolidayDao()
    }

    @Provides
    @Reusable
    fun provideAbsenceDao(omaLomaDb: OmaLomaDb): AbsenceDao {
        return omaLomaDb.absenceDao()
    }

}