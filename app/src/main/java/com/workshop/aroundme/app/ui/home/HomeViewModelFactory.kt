package com.workshop.aroundme.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.domain.interactor.place.GetPlacesUseCase
import com.workshop.aroundme.domain.interactor.place.StarPlaceUseCase


class HomeViewModelFactory(
    private val getPlacesUseCase: GetPlacesUseCase,
    private val starPlaceUseCase: StarPlaceUseCase,
    private val categoryRepository: CategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(getPlacesUseCase, starPlaceUseCase, categoryRepository) as T
    }

}