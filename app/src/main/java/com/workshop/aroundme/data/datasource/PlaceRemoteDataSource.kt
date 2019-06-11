package com.workshop.aroundme.data.datasource

import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.data.model.PlaceEntity
import io.reactivex.Single

interface PlaceRemoteDataSource{

    fun getFeaturedPlaces(): Single<List<PlaceEntity>?>

    fun getPlaceDetail(slug: String): Single<PlaceDetailEntity>
}