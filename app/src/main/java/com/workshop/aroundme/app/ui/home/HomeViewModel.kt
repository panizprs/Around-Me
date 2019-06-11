package com.workshop.aroundme.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.data.model.ParentCategory
import com.workshop.aroundme.data.model.Place
import com.workshop.aroundme.domain.interactor.base.None
import com.workshop.aroundme.domain.interactor.category.GetCategoriesUseCase
import com.workshop.aroundme.domain.interactor.place.GetPlacesUseCase
import com.workshop.aroundme.domain.interactor.place.StarPlaceUseCase


class HomeViewModel(
    private val getPlacesUseCase: GetPlacesUseCase,
    private val starPlaceUseCase: StarPlaceUseCase,
    private val categoryUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>> = _places

    private val _categories = MutableLiveData<List<ParentCategory>>()
    val categories: LiveData<List<ParentCategory>> = _categories


    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error


    fun loadHomePage() {
        getPlacesUseCase.execute(None(), ::getPlacesSuccess, ::fail)
        categoryUseCase.execute(None(), ::getCategoriesSuccess, ::fail)
    }

    private fun getPlacesSuccess(places : List<Place>?) {
        _places.value = places ?: emptyList()
        println("getPlaces: " + _categories.value?.size)
    }

    private fun getCategoriesSuccess(categories : List<ParentCategory>?) {
        _categories.value = categories ?: emptyList()
    }


    private fun fail(throwable: Throwable){
        _error.value = throwable
    }

    fun onItemStarred(place: Place) {
        starPlaceUseCase.execute(place)
    }
}