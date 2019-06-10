package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.remote.model.response.DetailResponseDto
import com.workshop.aroundme.remote.model.response.PlaceDto
import com.workshop.aroundme.remote.service.PlaceService
import io.reactivex.Single

class PlaceRemoteDataSource(private val placeService: PlaceService) {

    fun getFeaturedPlaces(): Single<List<PlaceDto>?> {
        return placeService.getPlaces()
            .map {featuredPlacesResponseDto ->
                featuredPlacesResponseDto.response?.items
            }
    }

    fun getPlaceDetail(slug: String): Single<DetailResponseDto?> {
        return placeService.getPlaceDetail(slug)
            .map {placeDetail ->
                placeDetail.response
            }
    }
}