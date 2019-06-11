package com.workshop.aroundme.local.datasource

import com.workshop.aroundme.data.datasource.PlaceDataSource
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.data.model.toLocalPlace
import com.workshop.aroundme.local.dao.PlaceDao
import com.workshop.aroundme.local.model.LocalPlace
import com.workshop.aroundme.local.model.toPlaceEntity

class PlaceLocalDataSource(private val placeDao: PlaceDao) : PlaceDataSource {

    override fun getStarredPlaces(): List<PlaceEntity> {
        return placeDao.listStarredPlaces().map {localPlace ->
            localPlace.toPlaceEntity()
        }
    }

    override fun starPlace(placeEntity: PlaceEntity) {
        placeDao.insert(placeEntity.toLocalPlace())
    }
}