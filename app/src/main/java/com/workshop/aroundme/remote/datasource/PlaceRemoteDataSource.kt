package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.data.datasource.PlaceDataSource
import com.workshop.aroundme.data.model.PlaceDetail
import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.remote.model.response.toPlaceEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.remote.model.response.DetailResponseDto
import com.workshop.aroundme.remote.model.response.toPlaceDetailEntity
import com.workshop.aroundme.remote.service.PlaceService
import io.reactivex.Single

class PlaceRemoteDataSource(private val placeService: PlaceService) {

    fun getFeaturedPlaces(): Single<List<PlaceEntity>?> {
        return placeService.getPlaces()
            .map {featuredPlacesResponseDto ->
                featuredPlacesResponseDto.response?.items?.map {placeDto ->
                    placeDto.toPlaceEntity()
                }
            }
    }

    fun getPlaceDetail(slug: String): Single<PlaceDetailEntity> {
        return placeService.getPlaceDetail(slug)
            .map { placeDetail ->
                placeDetail.response?.toPlaceDetailEntity()
            }
    }
}