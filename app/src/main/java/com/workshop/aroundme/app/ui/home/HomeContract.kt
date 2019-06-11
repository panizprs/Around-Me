package com.workshop.aroundme.app.ui.home

import com.workshop.aroundme.data.model.ParentCategory
import com.workshop.aroundme.data.model.Place


interface HomeContract {

    interface View : HomeContract {

        fun showPlaces(places: List<Place>)

        fun showCategories(categories: List<ParentCategory>)

    }

    interface Presenter: HomeContract {

        fun onActivityCreated()

        fun onItemStarred(place: Place)

        fun onDestroyView()

    }

}
