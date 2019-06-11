package com.workshop.aroundme.app.ui.starred

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.data.model.Place
import com.workshop.aroundme.domain.interactor.place.GetStarredPlacesUseCase
import com.workshop.aroundme.domain.interactor.place.StarPlaceUseCase

class StarredViewModel(
    private val getStarredPlacesUseCase: GetStarredPlacesUseCase,
    private val starPlaceUseCase: StarPlaceUseCase
) : ViewModel() {

    private val _starredPlaces = MutableLiveData<List<Place>>()
//    val getStarredPlaces : LiveData<List<PlaceEntity>> = _starredPlaces

    fun getStarredPlaces(): LiveData<List<Place>> {
        return _starredPlaces
    }


    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    fun onViewCreated() {
        getStarredPlacesUseCase.execute(GetStarredPlacesUseCase.None(), ::getPlacesSuccess, ::fail)
    }

    fun onItemStarred(place: Place) {
        starPlaceUseCase.execute(place)
    }

    private fun getPlacesSuccess(places: List<Place>?) {
        _starredPlaces.value = places ?: emptyList()
    }

    private fun fail(throwable: Throwable) {
        _error.value = throwable
    }
}