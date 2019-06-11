package com.workshop.aroundme.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.workshop.aroundme.domain.interactor.category.GetCategoriesUseCase
import com.workshop.aroundme.domain.interactor.place.GetPlacesUseCase
import com.workshop.aroundme.domain.interactor.place.SortPlacesUseCase
import com.workshop.aroundme.domain.interactor.place.StarPlaceUseCase
import javax.inject.Inject


class HomeViewModelFactory @Inject constructor(
    private val getPlacesUseCase: GetPlacesUseCase,
    private val starPlaceUseCase: StarPlaceUseCase,
    private val sortPlacesUseCase: SortPlacesUseCase,
    private val categoriesUseCase: GetCategoriesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(getPlacesUseCase, starPlaceUseCase,sortPlacesUseCase, categoriesUseCase) as T
    }

}