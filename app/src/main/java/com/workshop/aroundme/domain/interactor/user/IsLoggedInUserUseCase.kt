package com.workshop.aroundme.domain.interactor.user

import com.workshop.aroundme.domain.executor.PostExecutorThread
import com.workshop.aroundme.domain.executor.UseCaseExecutorThread
import com.workshop.aroundme.domain.interactor.base.CompletableUseCase
import com.workshop.aroundme.domain.interactor.base.None
import com.workshop.aroundme.domain.interactor.base.SingleUseCase
import com.workshop.aroundme.domain.model.User
import com.workshop.aroundme.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class IsLoggedInUserUseCase @Inject constructor(
    private val userRepository: UserRepository){

    fun isLoggedIn() = userRepository.isLoggedIn()
}