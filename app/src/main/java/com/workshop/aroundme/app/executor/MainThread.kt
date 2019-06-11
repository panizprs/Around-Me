package com.workshop.aroundme.app.executor

import com.workshop.aroundme.domain.executor.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainThread : PostExecutorThread{
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}