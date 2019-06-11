package com.workshop.aroundme.domain.interactor.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseUseCase {

    val disposables = CompositeDisposable()

    fun add(disposable: Disposable){
        disposables.add(disposable)
    }

    fun expose(){
        if(!disposables.isDisposed)
            disposables.dispose()
    }
}