package com.workshop.aroundme.domain.repository

import com.workshop.aroundme.data.model.Place
import com.workshop.aroundme.data.model.PlaceDetail
import com.workshop.aroundme.data.model.PlaceDetailEntity
import io.reactivex.Completable
import io.reactivex.Single

interface PlaceRepository {
    fun getFeaturedPlaces(): Single<List<Place>>

    fun getStarredPlaces(): Single<List<Place>>

    fun getPlaceDetail(slug: String): Single<PlaceDetail?>

    fun starPlace(place: Place): Completable

}