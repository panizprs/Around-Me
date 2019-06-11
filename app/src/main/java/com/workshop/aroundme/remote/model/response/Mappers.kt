package com.workshop.aroundme.remote.model.response

import com.workshop.aroundme.data.model.CategoryEntity
import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.local.model.LocalPlace

fun PlaceDto.toPlaceEntity() = PlaceEntity(
    name = full_name,
    address = address,
    likes = like_count,
    imageUrl = images?.getOrNull(0)?.image?.card?.url,
    isFavorite = is_liked != null && is_liked,
    placeId = place_id,
    slug = slug,
    total_views = total_views
)



fun DetailResponseDto.toPlaceDetailEntity() = PlaceDetailEntity(
    coverUrl = cover_image?.image?.medium?.url,
    name = full_name,
    categories = categories?.joinToString(separator = " ،") { category -> category?.name.toString() },
    address = address,
    location = latlng,
    tags = tags?.joinToString(separator = " ،") { tag -> tag?.name.toString() }
)

fun CategoryDto.toCategoryEntity() = CategoryEntity(
    id = id,
    name = name,
    icon = icon
)

