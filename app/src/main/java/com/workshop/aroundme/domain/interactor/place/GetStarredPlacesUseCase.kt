package com.workshop.aroundme.domain.interactor.place

import com.workshop.aroundme.data.model.Place
import com.workshop.aroundme.domain.executor.PostExecutorThread
import com.workshop.aroundme.domain.executor.UseCaseExecutorThread
import com.workshop.aroundme.domain.interactor.base.None
import com.workshop.aroundme.domain.interactor.base.SingleUseCase
import com.workshop.aroundme.domain.repository.PlaceRepository
import io.reactivex.Single
import javax.inject.Inject

class GetStarredPlacesUseCase @Inject constructor(
    private val placeRepository: PlaceRepository,
    postExecutorThread: PostExecutorThread,
    useCaseExecutorThread: UseCaseExecutorThread
) : SingleUseCase<None, List<Place>>(postExecutorThread, useCaseExecutorThread) {


    override fun buildSingle(param: None): Single<List<Place>> {
        return placeRepository.getStarredPlaces()
    }


}
