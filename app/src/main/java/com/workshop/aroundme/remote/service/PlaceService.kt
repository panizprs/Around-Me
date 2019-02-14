package com.workshop.aroundme.remote.service

import com.google.gson.Gson
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.model.response.FeaturedPlacesResponseDto

class PlaceService(private val networkManager: NetworkManager) {

    fun getFeaturedPlacesResponse(): FeaturedPlacesResponseDto {
        val rawData = networkManager.get(URL_FEATURED)
        return Gson().fromJson(rawData, FeaturedPlacesResponseDto::class.java)
    }

    companion object {
        const val URL_FEATURED = "https://restapis.xyz/around-me/v1/featured"
    }
}