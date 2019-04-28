package com.workshop.aroundme.app.ui.home

import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.data.repository.PlaceRepository

class HomePresenter(
    private val placeRepository: PlaceRepository,
    private val categoryRepository: CategoryRepository
) : HomeContract.Presenter {

    lateinit var view: HomeContract.View

    override fun onActivityCreated() {
        placeRepository.getFeaturedPlaces { featuredPlaces ->
            view.showPlaces(featuredPlaces ?: emptyList())
            categoryRepository.getCategories {
                view.showCategories(it ?: emptyList())
            }
        }
    }

    override fun onItemStarred(placeEntity: PlaceEntity) {
        placeRepository.starPlace(placeEntity)
    }

}
