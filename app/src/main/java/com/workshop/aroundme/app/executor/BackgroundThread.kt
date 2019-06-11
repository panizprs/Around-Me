package com.workshop.aroundme.app.executor

import com.workshop.aroundme.domain.executor.UseCaseExecutorThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class BackgroundThread : UseCaseExecutorThread{
    override val scheduler: Scheduler
        get() = Schedulers.io()

}