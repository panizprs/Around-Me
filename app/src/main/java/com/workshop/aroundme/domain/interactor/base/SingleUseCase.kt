package com.workshop.aroundme.domain.interactor.base

import com.workshop.aroundme.domain.executor.PostExecutorThread
import com.workshop.aroundme.domain.executor.UseCaseExecutorThread
import io.reactivex.Single

abstract class SingleUseCase<in Param, Result>(
    private val postExecutorThread: PostExecutorThread,
    private val useCaseExecutorThread: UseCaseExecutorThread
) : BaseUseCase() {

    abstract fun buildSingle(param: Param): Single<Result>

    fun execute(
        param: Param,
        onSuccess: (Result) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        buildSingle(param)
            .subscribeOn(useCaseExecutorThread.scheduler)
            .observeOn(postExecutorThread.scheduler)
            .subscribe({ result ->
                onSuccess(result)
            }, { error ->
                onFailure(error)
            }).also { disposable ->
                add(disposable)
            }
    }

}