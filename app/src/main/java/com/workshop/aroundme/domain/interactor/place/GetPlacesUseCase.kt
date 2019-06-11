package com.workshop.aroundme.domain.interactor.place

import com.workshop.aroundme.data.model.Place
import com.workshop.aroundme.data.repository.PlaceRepositoryImpl
import com.workshop.aroundme.domain.executor.PostExecutorThread
import com.workshop.aroundme.domain.executor.UseCaseExecutorThread
import com.workshop.aroundme.domain.interactor.base.None
import com.workshop.aroundme.domain.interactor.base.SingleUseCase
import io.reactivex.Single

class GetPlacesUseCase(
    private val placeRepository: PlaceRepositoryImpl,
    postExecutorThread: PostExecutorThread,
    useCaseExecutorThread: UseCaseExecutorThread
) : SingleUseCase<None, List<Place>>(postExecutorThread, useCaseExecutorThread) {


    override fun buildSingle(param: None): Single<List<Place>> {
        return placeRepository.getFeaturedPlaces()
    }

}
