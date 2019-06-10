package com.workshop.aroundme.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.data.model.CategoryEntity
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.data.repository.PlaceRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val placeRepository: PlaceRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _places = MutableLiveData<List<PlaceEntity>>()
    val places: LiveData<List<PlaceEntity>> = _places

    private val _categories = MutableLiveData<List<ParentCategoryEntity>>()
    val categories: LiveData<List<ParentCategoryEntity>> = _categories


    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val compositeDisposable = CompositeDisposable()

    fun loadHomePage() {
        placeRepository.getFeaturedPlaces()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _places.value = it ?: emptyList()
            }
            .observeOn(Schedulers.io())
            .flatMap {
                categoryRepository.getCategories()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _categories.value = it ?: emptyList()
            }, {
                _error.value = it
            }).addTo(compositeDisposable)

    }

    fun onItemStarred(placeEntity: PlaceEntity) {
        placeRepository.starPlace(placeEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(compositeDisposable)
    }
}