package com.workshop.aroundme.domain.interactor.place

import com.workshop.aroundme.data.model.Place
import com.workshop.aroundme.domain.executor.PostExecutorThread
import com.workshop.aroundme.domain.executor.UseCaseExecutorThread
import com.workshop.aroundme.domain.interactor.base.CompletableUseCase
import com.workshop.aroundme.domain.model.toPlaceEntity
import com.workshop.aroundme.domain.repository.PlaceRepository
import io.reactivex.Completable

class StarPlaceUseCase(
    private val placeRepository: PlaceRepository,
    postExecutorThread: PostExecutorThread,
    useCaseExecutorThread: UseCaseExecutorThread
) : CompletableUseCase<Place>(postExecutorThread, useCaseExecutorThread) {

    override fun buildCompletable(place: Place): Completable {
        return placeRepository.starPlace(place)
    }

}