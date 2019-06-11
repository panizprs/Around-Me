package com.workshop.aroundme.domain.executor

import io.reactivex.Scheduler

interface PostExecutorThread {
    val scheduler : Scheduler
}