package com.workshop.aroundme.local.datasource

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.workshop.aroundme.data.model.Place
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.local.AppDatabase
import com.workshop.aroundme.local.model.LocalPlace
import com.workshop.aroundme.local.model.toPlaceEntity
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.empty
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
class PlaceLocalDataSourceImplTest{

    lateinit var placeLocalDataSourceImpl: PlaceLocalDataSourceImpl
    lateinit var appDatabase: AppDatabase

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        placeLocalDataSourceImpl = PlaceLocalDataSourceImpl(appDatabase.placeDao())
    }


    @Test
    fun return_empty_when_place_table_is_empty(){
        assertEquals(placeLocalDataSourceImpl.getStarredPlaces(), emptyList<Place>())

        assertThat(placeLocalDataSourceImpl.getStarredPlaces(), `is`(empty()))
    }

    @Test
    fun starPlace_add_new_place_to_place_table(){
        val placeEntity = addPlaceToPlaceTable()
        assertEquals(listOf(placeEntity), placeLocalDataSourceImpl.getStarredPlaces())
    }


    @Test
    fun clearAllPlaces_return_empty_table(){
        addPlaceToPlaceTable()

        placeLocalDataSourceImpl.clearAllPlaces()

        assertEquals(placeLocalDataSourceImpl.getStarredPlaces(), listOf<PlaceEntity>())
    }


    @After
    fun tearDown(){
        appDatabase.clearAllTables()
        appDatabase.close()
    }

    private fun addPlaceToPlaceTable() : PlaceEntity {
        val localPlace = LocalPlace(1, "", "", true)
        val placeEntity = localPlace.toPlaceEntity()
        placeLocalDataSourceImpl.starPlace(placeEntity)
        return placeEntity
    }

}