package com.workshop.aroundme.app.ui.home

import com.workshop.aroundme.data.model.Place

interface OnHomePlaceItemClickListener {

    fun onPlaceItemClicked(place: Place)

    fun onItemStarred(place: Place)
}