package com.workshop.aroundme.remote.service

import com.google.gson.Gson
import com.workshop.aroundme.remote.model.response.DetailResponseDto
import com.workshop.aroundme.remote.model.response.FeaturedPlacesResponseDto
import com.workshop.aroundme.remote.model.response.PlaceDetailResponseDto

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PlaceService {

    @GET("v1/featured")
    fun getPlaces() : Single<FeaturedPlacesResponseDto?>


    @GET("v1/place/{slug}")
    fun getPlaceDetail(@Path(value = "slug", encoded = true) slug : String) : Single<PlaceDetailResponseDto?>




    companion object {
        const val BASE_URL = "http://restapis.xyz/around-me/"
    }
}