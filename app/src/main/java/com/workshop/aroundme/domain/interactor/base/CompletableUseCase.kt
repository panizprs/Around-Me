package com.workshop.aroundme.domain.interactor.base

import com.workshop.aroundme.domain.executor.PostExecutorThread
import com.workshop.aroundme.domain.executor.UseCaseExecutorThread
import io.reactivex.Completable

abstract class CompletableUseCase<in Param>(
    private val postExecutorThread: PostExecutorThread,
    private val useCaseExecutorThread: UseCaseExecutorThread
) : BaseUseCase() {

    abstract fun buildCompletable(param: Param): Completable

    fun execute(param: Param) {
        buildCompletable(param)
            .subscribeOn(useCaseExecutorThread.scheduler)
            .observeOn(postExecutorThread.scheduler)
            .subscribe()
            .also {disposable ->
                add(disposable)
            }
    }

}