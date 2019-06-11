package com.workshop.aroundme.app.di.module

import android.content.Context
import androidx.room.Room
import com.workshop.aroundme.local.AppDatabase
import com.workshop.aroundme.local.dao.PlaceDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule{

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "db.data").build()
    }

    @Provides
    @Singleton
    fun providePlaceDao(appDatabase: AppDatabase) : PlaceDao{
        return appDatabase.placeDao()
    }
}