package com.workshop.aroundme.app.ui.starred

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.workshop.aroundme.data.repository.PlaceRepository
import javax.inject.Inject

class StarredViewModelFactory @Inject constructor(private val placeRepository: PlaceRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StarredViewModel(placeRepository) as T
    }

}