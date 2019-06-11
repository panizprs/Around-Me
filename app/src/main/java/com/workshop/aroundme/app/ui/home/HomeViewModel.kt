package com.workshop.aroundme.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.data.model.Place
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.domain.interactor.place.GetPlacesUseCase
import com.workshop.aroundme.domain.interactor.place.StarPlaceUseCase
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    private val getPlacesUseCase: GetPlacesUseCase,
    private val starPlaceUseCase: StarPlaceUseCase,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>> = _places

    private val _categories = MutableLiveData<List<ParentCategoryEntity>>()
    val categories: LiveData<List<ParentCategoryEntity>> = _categories


    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val compositeDisposable = CompositeDisposable()

    fun loadHomePage() {
        getPlacesUseCase.execute(GetPlacesUseCase.None(), ::getPlacesSuccess, ::fail)
//            .flatMap {
//                categoryRepository.getCategories()
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                _categories.value = it ?: emptyList()
//            }, {
//                _error.value = it
//            }).addTo(compositeDisposable)

    }

    private fun getPlacesSuccess(places : List<Place>?) {
        _places.value = places ?: emptyList()
    }

    private fun fail(throwable: Throwable){
        _error.value = throwable
    }

    fun onItemStarred(place: Place) {
        starPlaceUseCase.execute(place)
    }
}