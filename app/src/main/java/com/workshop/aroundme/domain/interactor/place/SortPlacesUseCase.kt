package com.workshop.aroundme.domain.interactor.place


import com.workshop.aroundme.data.model.Place
import javax.inject.Inject


class SortPlacesUseCase @Inject constructor(){

    lateinit var places : List<Place>


    fun sort(SortType: Int) : List<Place> {
        var sortedPlaces : List<Place> = places
        when(SortType) {
            SORT_DEFAULT -> {
                sortedPlaces = places
            }
            SORT_BASED_LIKES_COUNT -> {
                sortedPlaces = places.sortedByDescending { place ->
                    place.likes
                }
            }
            SORT_BASED_TOTAL_VIEWS -> {
                sortedPlaces = places.sortedByDescending { place ->
                    place.total_views
                }
                println(places.map { place -> place.total_views })
            }
        }
        return sortedPlaces
    }


    companion object {
        const val SORT_DEFAULT = 0
        const val SORT_BASED_LIKES_COUNT = 1
        const val SORT_BASED_TOTAL_VIEWS = 2
    }
}