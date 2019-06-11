package com.workshop.aroundme.app.di

import com.workshop.aroundme.app.AroundMeApp
import com.workshop.aroundme.app.di.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentsModule::class,
        HomeModule::class,
        LoginModule::class]
)
interface AppComponent : AndroidInjector<AroundMeApp> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<AroundMeApp>

}