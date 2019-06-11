package com.workshop.aroundme.app.di.module

import com.workshop.aroundme.app.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{
    @ContributesAndroidInjector
    abstract fun mainActivity() : MainActivity
}