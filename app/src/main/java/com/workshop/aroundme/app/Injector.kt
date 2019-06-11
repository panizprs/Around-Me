package com.workshop.aroundme.app

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.workshop.aroundme.app.executor.BackgroundThread
import com.workshop.aroundme.app.executor.MainThread
import com.workshop.aroundme.data.repository.CategoryRepositoryImpl
import com.workshop.aroundme.data.repository.PlaceRepositoryImpl
import com.workshop.aroundme.data.repository.UserRepository
import com.workshop.aroundme.domain.interactor.category.GetCategoriesUseCase
import com.workshop.aroundme.domain.interactor.place.GetPlacesUseCase
import com.workshop.aroundme.domain.interactor.place.GetStarredPlacesUseCase
import com.workshop.aroundme.domain.interactor.place.StarPlaceUseCase
import com.workshop.aroundme.domain.repository.CategoryRepository
import com.workshop.aroundme.local.AppDatabase
import com.workshop.aroundme.local.datasource.PlaceLocalDataSource
import com.workshop.aroundme.local.datasource.UserLocalDataSource
import com.workshop.aroundme.remote.datasource.CategoryRemoteDataSource
import com.workshop.aroundme.remote.datasource.PlaceRemoteDataSource
import com.workshop.aroundme.remote.service.CategoryService
import com.workshop.aroundme.remote.service.PlaceService
import com.workshop.aroundme.remote.service.PlaceService.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Injector {

    private fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "db.data").build()
    }


    fun provideRetrofitClient() = Retrofit
        .Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .baseUrl(BASE_URL)
        .build()

    fun providePlaceService(retrofit: Retrofit) = retrofit.create(PlaceService::class.java)

    fun provideCategoryService(retrofit: Retrofit) = retrofit.create(CategoryService::class.java)


    fun provideCategoryUseCase() : GetCategoriesUseCase{
        return GetCategoriesUseCase(provideCategoryRepository(),
            MainThread(),
            BackgroundThread())
    }

    fun provideCategoryRepository(): CategoryRepository {
        return CategoryRepositoryImpl(CategoryRemoteDataSource(provideCategoryService(provideRetrofitClient())))
    }

    fun provideStarPlaceUseCase(context: Context): StarPlaceUseCase {
        return StarPlaceUseCase(
            providePlaceRepository(context),
            MainThread(),
            BackgroundThread()
        )
    }

    fun provideStarredPlacesUseCase(context: Context) : GetStarredPlacesUseCase {
        return GetStarredPlacesUseCase(
            providePlaceRepository(context),
            MainThread(),
            BackgroundThread()
        )
    }

    fun providePlacesUseCase(context: Context): GetPlacesUseCase {
        return GetPlacesUseCase(
            providePlaceRepository(context),
            MainThread(),
            BackgroundThread()
        )
    }

    fun providePlaceRepository(context: Context): PlaceRepositoryImpl {
        return PlaceRepositoryImpl(
            PlaceLocalDataSource(
                provideAppDatabase(context).placeDao()
            ),
            PlaceRemoteDataSource(
                providePlaceService(provideRetrofitClient())
            )
        )
    }

    fun provideUserRepository(context: Context): UserRepository {
        return UserRepository(
            UserLocalDataSource(
                provideDefaultSharedPref(context)
            )
        )
    }

    private fun provideDefaultSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences("user.data", Context.MODE_PRIVATE)
    }

}