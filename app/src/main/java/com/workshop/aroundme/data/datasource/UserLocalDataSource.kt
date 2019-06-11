package com.workshop.aroundme.data.datasource

import com.workshop.aroundme.data.model.UserEntity

interface UserLocalDataSource{
    fun login(user: UserEntity)

    fun logout()


    fun getUser(): UserEntity?


    companion object {
        const val KEY_USER = "user"
    }
}