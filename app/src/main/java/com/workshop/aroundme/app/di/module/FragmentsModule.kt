package com.workshop.aroundme.app.di.module

import com.workshop.aroundme.app.MainActivity
import com.workshop.aroundme.app.ui.detail.DetailFragment
import com.workshop.aroundme.app.ui.home.HomeFragment
import com.workshop.aroundme.app.ui.login.LoginFragment
import com.workshop.aroundme.app.ui.starred.StarredFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule{

    @ContributesAndroidInjector
    abstract fun homeFragment() : HomeFragment

    @ContributesAndroidInjector
    abstract fun loginFragment() : LoginFragment

    @ContributesAndroidInjector
    abstract fun starredFragment() : StarredFragment

    @ContributesAndroidInjector
    abstract fun detailFragment() : DetailFragment
}