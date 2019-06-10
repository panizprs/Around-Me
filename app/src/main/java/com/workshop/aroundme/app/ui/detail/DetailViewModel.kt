package com.workshop.aroundme.app.ui.detail

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.data.repository.PlaceRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DetailViewModel(private val repository: PlaceRepository) : ViewModel() {

    private val _placeDetail = MutableLiveData<PlaceDetailEntity>()
    val placeDetail: LiveData<PlaceDetailEntity> = _placeDetail

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error


    fun getPlaceDetail(slug: String?) {
        slug?.let { mySlug ->
            repository.getPlaceDetail(slug)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ placeDetailEntity ->
                    _placeDetail.value = placeDetailEntity
                },
                    {
                        _error.value = it
                    }

                )
        } ?: run {
            _error.value = Throwable("Slug must not be null")
        }


    }


}