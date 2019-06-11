package com.workshop.aroundme.app.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.workshop.aroundme.data.repository.PlaceRepositoryImpl
import com.workshop.aroundme.domain.repository.PlaceRepository
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor(private val repository: PlaceRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }

}