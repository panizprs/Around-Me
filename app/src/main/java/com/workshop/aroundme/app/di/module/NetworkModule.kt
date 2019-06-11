package com.workshop.aroundme.app.di.module

import com.workshop.aroundme.remote.service.CategoryService
import com.workshop.aroundme.remote.service.PlaceService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule{

    @Provides
    fun provideRetrofitClient() = Retrofit
        .Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .baseUrl(PlaceService.BASE_URL)
        .build()

    @Provides
    fun providePlaceService(retrofit: Retrofit) = retrofit.create(PlaceService::class.java)

    @Provides
    fun provideCategoryService(retrofit: Retrofit) = retrofit.create(CategoryService::class.java)

}