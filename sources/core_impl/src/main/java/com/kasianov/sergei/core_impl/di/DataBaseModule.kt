package com.kasianov.sergei.core_impl.di

import android.content.Context
import androidx.room.Room
import com.kasianov.sergei.core_api.database.AbsenceDao
import com.kasianov.sergei.core_api.database.DataBaseContract
import com.kasianov.sergei.core_api.database.PublicHolidayDao
import com.kasianov.sergei.core_api.database.UserDao
import com.kasianov.sergei.core_impl.DATA_BASE_NAME
import com.kasianov.sergei.core_impl.OmaLomaDb
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Context): DataBaseContract {
        return Room
            .databaseBuilder(
                context.applicationContext, OmaLomaDb::class.java,
                DATA_BASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Reusable
    fun provideUserDao(omaLomaDbContract: DataBaseContract): UserDao {
        return omaLomaDbContract.userDao()
    }

    @Provides
    @Reusable
    fun providePublicHolidayDao(omaLomaDbContract: DataBaseContract): PublicHolidayDao {
        return omaLomaDbContract.publicHolidayDao()
    }

    @Provides
    @Reusable
    fun provideAbsenceDao(omaLomaDbContract: DataBaseContract): AbsenceDao {
        return omaLomaDbContract.absenceDao()
    }

}