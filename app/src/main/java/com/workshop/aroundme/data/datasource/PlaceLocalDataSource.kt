package com.workshop.aroundme.data.datasource

import com.workshop.aroundme.data.model.PlaceEntity

interface PlaceLocalDataSource{
    fun getStarredPlaces(): List<PlaceEntity>

    fun starPlace(placeEntity: PlaceEntity)

    fun clearAllPlaces()
}