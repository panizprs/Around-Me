package com.workshop.aroundme.data.repository

import com.workshop.aroundme.data.datasource.PlaceLocalDataSource
import com.workshop.aroundme.data.datasource.PlaceRemoteDataSource
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.data.model.toPlace
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class PlaceRepositoryImplTest {
    private val placeRemoteDataSource: PlaceRemoteDataSource = mockk()
    private val placeLocalDataSource: PlaceLocalDataSource = mockk()
    private val placeRepositoryImpl = PlaceRepositoryImpl(placeLocalDataSource, placeRemoteDataSource)

    @Before
    fun setUp(){
        clearAllMocks()
    }


    @Test
    fun `getPlaces when successful and list is empty`() {

        val places : List<PlaceEntity> = listOf()

        every { placeRemoteDataSource.getFeaturedPlaces() } returns Single.just(places)

        placeRepositoryImpl.getFeaturedPlaces()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(places.map { placeEntity -> placeEntity.toPlace() })

    }

    @Test
    fun `getPlaces when successful and list isn't empty`(){

        val place = PlaceEntity(1, "", "", 0, "", false, "", 0 )
        val places : List<PlaceEntity> =  listOf(place, place, place)

        every { placeRemoteDataSource.getFeaturedPlaces() } returns Single.just(places)

        placeRepositoryImpl.getFeaturedPlaces()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(places.map { placeEntity ->  placeEntity.toPlace()})

    }

    @Test
    fun `getPlaces when fail`(){

        every { placeRemoteDataSource.getFeaturedPlaces() } returns Single.error(Exception())

        placeRepositoryImpl.getFeaturedPlaces()
            .test()
            .assertNotComplete()
            .assertFailure(Exception::class.java)

    }

}