package com.workshop.aroundme.app.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.data.model.PlaceDetail
import com.workshop.aroundme.data.model.PlaceDetailEntity
import com.workshop.aroundme.data.repository.PlaceRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DetailViewModel(private val repository: PlaceRepositoryImpl) : ViewModel() {

    private val _placeDetail = MutableLiveData<PlaceDetail>()
    val placeDetail: LiveData<PlaceDetail> = _placeDetail

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error


    fun getPlaceDetail(slug: String?) {
        slug?.let { notNullSlug ->
            repository.getPlaceDetail(notNullSlug)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ placeDetail ->
                    _placeDetail.value = placeDetail
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