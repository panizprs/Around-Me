package com.workshop.aroundme.app.ui.starred

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.workshop.aroundme.domain.interactor.place.GetStarredPlacesUseCase
import com.workshop.aroundme.domain.interactor.place.StarPlaceUseCase
import javax.inject.Inject

class StarredViewModelFactory @Inject constructor(
    private val getStarredPlacesUseCase: GetStarredPlacesUseCase,
    private val starPlacesUseCase: StarPlaceUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StarredViewModel(getStarredPlacesUseCase, starPlacesUseCase) as T
    }

}