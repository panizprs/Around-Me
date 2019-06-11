package com.workshop.aroundme.domain.interactor.user

import com.workshop.aroundme.domain.executor.PostExecutorThread
import com.workshop.aroundme.domain.executor.UseCaseExecutorThread
import com.workshop.aroundme.domain.interactor.base.CompletableUseCase
import com.workshop.aroundme.domain.model.User
import com.workshop.aroundme.domain.repository.UserRepository
import io.reactivex.Completable
import javax.inject.Inject


class LoginUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    postExecutorThread: PostExecutorThread,
    useCaseExecutorThread: UseCaseExecutorThread
) : CompletableUseCase<User>(postExecutorThread, useCaseExecutorThread){

    override fun buildCompletable(user: User): Completable {
        return Completable.fromCallable{
            userRepository.login(user)
        }
    }

}