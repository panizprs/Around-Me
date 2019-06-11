package com.workshop.aroundme.domain.repository

import com.workshop.aroundme.domain.model.User


interface UserRepository{
    fun login(user: User)

    fun isLoggedIn() : Boolean
}