package com.workshop.aroundme.app.ui.home

import com.workshop.aroundme.data.model.Place
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.data.repository.PlaceRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

class HomePresenter(
    private val placeRepository: PlaceRepositoryImpl,
    private val categoryRepository: CategoryRepository
) : HomeContract.Presenter {

    lateinit var view: WeakReference<HomeContract.View>

    private var compositeDisposable = CompositeDisposable()

    override fun onActivityCreated() {
        placeRepository.getFeaturedPlaces()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                view.get()?.showPlaces(it ?: emptyList())
            }
            .observeOn(Schedulers.io())
            .flatMap {
                categoryRepository.getCategories()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.get()?.showCategories(it ?: emptyList())
            }, {

            })
            .addTo(compositeDisposable)
    }

    override fun onItemStarred(place : Place) {
        placeRepository.starPlace(place)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        compositeDisposable.dispose()
    }

}
