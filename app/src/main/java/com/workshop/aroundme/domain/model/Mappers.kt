package com.workshop.aroundme.domain.model

import com.workshop.aroundme.data.model.Place
import com.workshop.aroundme.data.model.PlaceDetail
import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.remote.model.response.DetailResponseDto

fun Place.toPlaceEntity() = PlaceEntity(
    placeId = placeId,
    name = name,
    likes = likes,
    imageUrl = imageUrl,
    address = address,
    slug = slug,
    isFavorite = isFavorite
)

fun PlaceDetailEntity.toPlaceDetail() = PlaceDetail(
    coverUrl = coverUrl,
    name = name,
    categories = categories,
    address = address,
    location = location,
    tags = tags
)