package com.workshop.aroundme.data.model

import io.mockk.clearAllMocks
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MappersKtTest{
    @Before
    fun setUp() = clearAllMocks()

    @Test
    fun `toPlace when placeEntity is not null`(){
        val placeEntity : PlaceEntity = PlaceEntity(1, "", "", 0, "", false, "", 0 )
        val place =  placeEntity.toPlace()

        checkPlaceEntityAndPlaceAreEqual(placeEntity, place)
    }


    @Test
    fun `toPlace when placeEntity name is null`(){
        val placeEntity = PlaceEntity(1, null, "", 0, "", false, "", 0 )
        val place = placeEntity.toPlace()

        checkPlaceEntityAndPlaceAreEqual(placeEntity, place)
    }


    private fun checkPlaceEntityAndPlaceAreEqual(placeEntity : PlaceEntity, place : Place){
        assertEquals(place.total_views, placeEntity.total_views)
        assertEquals(place.likes, placeEntity.likes)
        assertEquals(place.address, placeEntity.address)
        assertEquals(place.imageUrl, placeEntity.imageUrl)
        assertEquals(place.isFavorite, placeEntity.isFavorite)
        assertEquals(place.name, placeEntity.name)
        assertEquals(place.placeId, placeEntity.placeId)
        assertEquals(place.slug, placeEntity.slug)
    }
}