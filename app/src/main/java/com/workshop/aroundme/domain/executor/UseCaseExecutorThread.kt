package com.workshop.aroundme.domain.executor

import io.reactivex.Scheduler

interface UseCaseExecutorThread{
    val scheduler : Scheduler
}