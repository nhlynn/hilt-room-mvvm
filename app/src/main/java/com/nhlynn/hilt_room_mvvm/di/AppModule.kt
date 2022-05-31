package com.nhlynn.hilt_room_mvvm.di

import android.app.Application
import com.nhlynn.hilt_room_mvvm.db.AppDao
import com.nhlynn.hilt_room_mvvm.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getAppDB(context: Application) : AppDatabase{
        return AppDatabase.getAppDB(context)
    }

    @Singleton
    @Provides
    fun getAppDao(appDB : AppDatabase) : AppDao{
        return appDB.getAppDao()
    }
}