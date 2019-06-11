package com.workshop.aroundme.app.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.workshop.aroundme.data.repository.PlaceRepositoryImpl

class DetailViewModelFactory(private val repository: PlaceRepositoryImpl) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }

}