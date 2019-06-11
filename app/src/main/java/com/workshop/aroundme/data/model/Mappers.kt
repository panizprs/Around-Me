package com.workshop.aroundme.data.model

import com.workshop.aroundme.local.model.LocalPlace

fun PlaceEntity.toPlace() = Place(
    placeId = placeId,
    name = name,
    likes = likes,
    imageUrl = imageUrl,
    address = address,
    slug = slug,
    isFavorite = isFavorite
)


fun PlaceEntity.toLocalPlace() = LocalPlace(
    name = name ?: "",
    placeId = placeId,
    address = address ?: "",
    isStarred = isFavorite
)

