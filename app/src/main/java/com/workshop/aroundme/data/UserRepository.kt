package com.workshop.aroundme.data

import com.workshop.aroundme.local.datasource.UserLocalDataSource

class UserRepository(private val localDataSource: UserLocalDataSource) {

    fun login() {
        localDataSource.login()
    }

    fun isLoggedIn() = localDataSource.isLoggedIn()

}