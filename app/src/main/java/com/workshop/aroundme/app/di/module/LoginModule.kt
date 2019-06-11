package com.workshop.aroundme.app.di.module

import android.content.SharedPreferences
import com.workshop.aroundme.data.datasource.UserLocalDataSource
import com.workshop.aroundme.data.repository.UserRepositoryImpl
import com.workshop.aroundme.domain.repository.UserRepository
import com.workshop.aroundme.local.datasource.UserLocalDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class LoginModule {


    @Provides
    fun provideUserLocalDataSource(sharedPreferences: SharedPreferences) : UserLocalDataSource{
        return UserLocalDataSourceImpl(sharedPreferences)
    }


    @Provides
    fun provideUserRepository(userLocalDataSource: UserLocalDataSource): UserRepository {
        return UserRepositoryImpl(userLocalDataSource)
    }
}