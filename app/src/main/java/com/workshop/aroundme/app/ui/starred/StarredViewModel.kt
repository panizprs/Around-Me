package com.workshop.aroundme.app.ui.starred

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.data.repository.PlaceRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class StarredViewModel(private val placeRepository: PlaceRepository) : ViewModel(){

    private val _StarredPlaces = MutableLiveData<List<PlaceEntity>>()
//    val getStarredPlaces : LiveData<List<PlaceEntity>> = _StarredPlaces

    private val disposables = CompositeDisposable()

    fun getStarredPlaces() : LiveData<List<PlaceEntity>>{
        return _StarredPlaces
    }


    fun onViewCreated(){
        placeRepository.getStarredPlaces()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({places ->
                _StarredPlaces.value = places
            },{
                println("error $it")
            }).addTo(disposables)
    }

    fun onDestroyView(){
        if(!disposables.isDisposed)
            disposables.dispose()
    }

}