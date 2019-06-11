package com.workshop.aroundme.data.model

import com.workshop.aroundme.local.model.LocalPlace

fun PlaceEntity.toPlace() = Place(
    placeId = placeId,
    name = name,
    likes = likes,
    imageUrl = imageUrl,
    address = address,
    slug = slug,
    isFavorite = isFavorite,
    total_views = total_views
)


fun PlaceEntity.toLocalPlace() = LocalPlace(
    name = name ?: "",
    placeId = placeId,
    address = address ?: "",
    isStarred = isFavorite
)

fun CategoryEntity.toCategory()  = Category(
    icon = icon,
    id = id,
    name = name
)

fun ParentCategoryEntity.toParentCategory() = ParentCategory(
    name = name,
    children = children?.map { categoryEntity -> categoryEntity.toCategory() }
)


fun PlaceDetailEntity.toPlaceDetail() = PlaceDetail(
    coverUrl = coverUrl,
    name = name,
    categories = categories,
    address = address,
    location = location,
    tags = tags
)