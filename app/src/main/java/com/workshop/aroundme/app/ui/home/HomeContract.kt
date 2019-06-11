package com.workshop.aroundme.app.ui.home

import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.data.model.Place
import com.workshop.aroundme.data.model.PlaceEntity

interface HomeContract {

    interface View : HomeContract {

        fun showPlaces(places: List<Place>)

        fun showCategories(categories: List<ParentCategoryEntity>)

    }

    interface Presenter: HomeContract {

        fun onActivityCreated()

        fun onItemStarred(place: Place)

        fun onDestroyView()

    }

}
