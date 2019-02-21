package com.workshop.aroundme.app.ui.home

import com.workshop.aroundme.data.model.PlaceEntity

interface OnHomePlaceItemClickListener {

    fun onItemStarred(placeEntity: PlaceEntity)
}