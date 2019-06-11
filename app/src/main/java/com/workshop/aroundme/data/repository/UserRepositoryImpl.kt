package com.workshop.aroundme.data.repository


import com.workshop.aroundme.data.datasource.UserLocalDataSource
import com.workshop.aroundme.domain.model.User
import com.workshop.aroundme.domain.model.toUserEntity
import com.workshop.aroundme.domain.repository.UserRepository

class UserRepositoryImpl(private val localDataSource: UserLocalDataSource) : UserRepository{

    override fun login(user: User) {
        localDataSource.login(user.toUserEntity())
    }

    override fun isLoggedIn() = localDataSource.getUser() != null

}