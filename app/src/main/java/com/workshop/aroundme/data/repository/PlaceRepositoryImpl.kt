package com.workshop.aroundme.data.repository

import androidx.annotation.WorkerThread
import com.workshop.aroundme.data.model.*
import com.workshop.aroundme.domain.model.toPlaceDetail
import com.workshop.aroundme.domain.model.toPlaceEntity
import com.workshop.aroundme.domain.repository.PlaceRepository
import com.workshop.aroundme.local.datasource.PlaceLocalDataSource
import com.workshop.aroundme.remote.datasource.PlaceRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single

class PlaceRepositoryImpl(
    private val placeLocalDataSource: PlaceLocalDataSource,
    private val placeRemoteDataSource: PlaceRemoteDataSource
) : PlaceRepository {

    override fun getFeaturedPlaces(): Single<List<Place>> {
        return placeRemoteDataSource.getFeaturedPlaces().map { placeEntities ->
            placeEntities.map { placeEntity ->
                placeEntity.toPlace()
            }
        }
    }

    override fun getPlaceDetail(slug: String): Single<PlaceDetail?> {
        return placeRemoteDataSource.getPlaceDetail(slug).map { detailResponseDto ->
            detailResponseDto.toPlaceDetail()
        }
    }

    @WorkerThread
    override fun getStarredPlaces(): Single<List<Place>> {
        return Single.fromCallable {
            placeLocalDataSource.getStarredPlaces().map { placeEntity ->
                placeEntity.toPlace()
            }
        }
    }

    @WorkerThread
    override fun starPlace(place: Place): Completable {
        return Completable.fromCallable {
            placeLocalDataSource.starPlace(place.toPlaceEntity())
        }
    }
}
