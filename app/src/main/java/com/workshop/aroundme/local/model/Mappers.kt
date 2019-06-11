package com.workshop.aroundme.local.model

import com.workshop.aroundme.data.model.PlaceEntity

fun LocalPlace.toPlaceEntity() = PlaceEntity(
    name = name,
    address = address,
    likes = 0,
    imageUrl = null,
    isFavorite = isStarred,
    placeId = placeId,
    slug = null,
    total_views = 0
)