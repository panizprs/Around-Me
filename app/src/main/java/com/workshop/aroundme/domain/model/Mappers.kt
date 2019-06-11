package com.workshop.aroundme.domain.model

import com.workshop.aroundme.data.model.*
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

fun User.toUserEntity() = UserEntity(
    userName = userName
)