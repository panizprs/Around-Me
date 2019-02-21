package com.workshop.aroundme.data

import androidx.annotation.WorkerThread
import com.workshop.aroundme.data.mapper.toLocalPlace
import com.workshop.aroundme.data.mapper.toPlaceEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.local.datasource.PlaceLocalDataSource
import com.workshop.aroundme.remote.datasource.PlaceRemoteDataSource
import kotlin.concurrent.thread

class PlaceRepository(
    private val placeLocalDataSource: PlaceLocalDataSource,
    private val placeRemoteDataSource: PlaceRemoteDataSource
) {

    fun getFeaturedPlaces(success: (List<PlaceEntity>?) -> Unit) {
        Thread {
            val result = placeRemoteDataSource.getFeaturedPlaces()?.map { placeDto ->
                placeDto.toPlaceEntity()
            }
            success(result)
        }.start()
    }

    @WorkerThread
    fun getStarredPlaces(success: (List<PlaceEntity>) -> Unit) {
        thread {
            val places = placeLocalDataSource.getStarredPlaces().map { localPlace ->
                localPlace.toPlaceEntity()
            }
            success(places)
        }
    }

    @WorkerThread
    fun starPlace(placeEntity: PlaceEntity) {
        thread {
            placeLocalDataSource.starPlace(placeEntity.toLocalPlace())
        }
    }
}