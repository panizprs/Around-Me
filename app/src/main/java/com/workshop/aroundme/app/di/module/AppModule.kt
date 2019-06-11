package com.workshop.aroundme.app.di.module


import android.content.Context
import android.content.SharedPreferences
import com.workshop.aroundme.app.AroundMeApp
import com.workshop.aroundme.app.executor.BackgroundThread
import com.workshop.aroundme.app.executor.MainThread
import com.workshop.aroundme.domain.executor.PostExecutorThread
import com.workshop.aroundme.domain.executor.UseCaseExecutorThread
import dagger.Module
import dagger.Provides

@Module
class AppModule{

    @Provides
    fun provideContext(app : AroundMeApp) = app.applicationContext

    @Provides
    fun provideMainThread() : PostExecutorThread {
        return MainThread()
    }


    @Provides
    fun provideBackgroundThread() : UseCaseExecutorThread {
        return BackgroundThread()
    }


    @Provides
    fun provideDefaultSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences("user.data", Context.MODE_PRIVATE)
    }
}