package com.workshop.aroundme.data.datasource

import com.workshop.aroundme.data.model.PlaceEntity

interface PlaceDataSource{
    fun getStarredPlaces(): List<PlaceEntity>

    fun starPlace(placeEntity: PlaceEntity)
}