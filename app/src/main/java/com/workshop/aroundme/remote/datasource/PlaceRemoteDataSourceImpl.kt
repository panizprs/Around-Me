package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.data.datasource.PlaceRemoteDataSource
import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.remote.model.response.toPlaceEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.remote.model.response.toPlaceDetailEntity
import com.workshop.aroundme.remote.service.PlaceService
import io.reactivex.Single

class PlaceRemoteDataSourceImpl(private val placeService: PlaceService) : PlaceRemoteDataSource {

    override fun getFeaturedPlaces(): Single<List<PlaceEntity>?> {
        return placeService.getPlaces()
            .map {featuredPlacesResponseDto ->
                featuredPlacesResponseDto.response?.items?.map {placeDto ->
                    placeDto.toPlaceEntity()
                }
            }
    }

    override fun getPlaceDetail(slug: String): Single<PlaceDetailEntity> {
        return placeService.getPlaceDetail(slug)
            .map { placeDetail ->
                placeDetail.response?.toPlaceDetailEntity()
            }
    }
}