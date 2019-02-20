package com.workshop.aroundme.data

import com.workshop.aroundme.data.mapper.toPlaceEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.remote.datasource.PlaceRemoteDataSource

class PlaceRepository(private val placeRemoteDataSource: PlaceRemoteDataSource) {

    fun getFeaturedPlaces(success: (List<PlaceEntity>?) -> Unit) {
        Thread {
            val result = placeRemoteDataSource.getFeaturedPlaces()?.map { placeDto ->
                placeDto.toPlaceEntity()
            }
            success(result)
        }.start()
    }
}