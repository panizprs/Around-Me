package com.workshop.aroundme.app.di.module

import com.workshop.aroundme.data.datasource.CategoryRemoteDataSource
import com.workshop.aroundme.data.datasource.PlaceLocalDataSource
import com.workshop.aroundme.data.datasource.PlaceRemoteDataSource
import com.workshop.aroundme.data.repository.CategoryRepositoryImpl
import com.workshop.aroundme.data.repository.PlaceRepositoryImpl
import com.workshop.aroundme.domain.repository.CategoryRepository
import com.workshop.aroundme.domain.repository.PlaceRepository
import com.workshop.aroundme.local.dao.PlaceDao

import com.workshop.aroundme.local.datasource.PlaceLocalDataSourceImpl
import com.workshop.aroundme.remote.datasource.CategoryRemoteDataSourceImpl
import com.workshop.aroundme.remote.datasource.PlaceRemoteDataSourceImpl
import com.workshop.aroundme.remote.service.CategoryService
import com.workshop.aroundme.remote.service.PlaceService
import dagger.Module
import dagger.Provides

@Module
class HomeModule{


    @Provides
    fun providePlaceRemoteDataSource(placeService : PlaceService) : PlaceRemoteDataSource{
        return PlaceRemoteDataSourceImpl(placeService)
    }

    @Provides
    fun providePlaceLocalDataSource(placeDao : PlaceDao) : PlaceLocalDataSource {
        return PlaceLocalDataSourceImpl(placeDao)
    }


    @Provides
    fun providePlaceRepository(placeLocalDataSource: PlaceLocalDataSource, placeRemoteDataSource: PlaceRemoteDataSource): PlaceRepository {
        return PlaceRepositoryImpl(placeLocalDataSource,placeRemoteDataSource)
    }


    @Provides
    fun provideCategoryRemoteDataSource(categoryService: CategoryService) : CategoryRemoteDataSource {
        return CategoryRemoteDataSourceImpl(categoryService)

    }

    @Provides
    fun provideCategoryRepository(categoryRemoteDataSource: CategoryRemoteDataSource): CategoryRepository {
        return CategoryRepositoryImpl(categoryRemoteDataSource)
    }
}