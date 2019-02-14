package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.remote.model.response.PlaceDto
import com.workshop.aroundme.remote.service.PlaceService

class PlaceDataSource(private val placeService: PlaceService) {

    fun getFeaturedPlaces(): List<PlaceDto>? {
        return placeService.getFeaturedPlacesResponse()
            .response?.items
    }
}