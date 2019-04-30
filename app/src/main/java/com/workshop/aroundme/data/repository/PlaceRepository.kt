package com.workshop.aroundme.data.repository

import androidx.annotation.WorkerThread
import com.workshop.aroundme.data.mapper.toLocalPlace
import com.workshop.aroundme.data.mapper.toPlaceDetailEntity
import com.workshop.aroundme.data.mapper.toPlaceEntity
import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.local.datasource.PlaceLocalDataSource
import com.workshop.aroundme.remote.datasource.PlaceRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single
import kotlin.concurrent.thread

class PlaceRepository(
    private val placeLocalDataSource: PlaceLocalDataSource,
    private val placeRemoteDataSource: PlaceRemoteDataSource
) {

    fun getFeaturedPlaces(): Single<List<PlaceEntity>> {
        return Single.fromCallable {
            placeRemoteDataSource.getFeaturedPlaces()?.map { placeDto ->
                placeDto.toPlaceEntity()
            }
        }
    }

    fun getPlaceDetail(slug: String, success: (PlaceDetailEntity?) -> Unit) {
        thread {
            val entity = placeRemoteDataSource.getPlaceDetail(slug)?.toPlaceDetailEntity()
            success(entity)
        }
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
    fun starPlace(placeEntity: PlaceEntity): Completable {
        return Completable.fromCallable {
            placeLocalDataSource.starPlace(placeEntity.toLocalPlace())
        }
    }
}
