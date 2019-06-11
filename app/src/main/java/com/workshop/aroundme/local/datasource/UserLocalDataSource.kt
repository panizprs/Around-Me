package com.workshop.aroundme.local.datasource

import android.content.SharedPreferences
import com.google.gson.Gson
import com.workshop.aroundme.data.datasource.UserLocalDataSource
import com.workshop.aroundme.data.datasource.UserLocalDataSource.Companion.KEY_USER
import com.workshop.aroundme.data.model.UserEntity

class UserLocalDataSourceImpl(private val sharedPreferences: SharedPreferences) : UserLocalDataSource {

    override fun login(user: UserEntity) {
        val userData = Gson().toJson(user, UserEntity::class.java)
        sharedPreferences.edit().putString(KEY_USER, userData).commit()
    }

    override fun logout() {
        sharedPreferences.edit().remove(KEY_USER).commit()
    }

    override fun getUser(): UserEntity? {
        val userData = sharedPreferences.getString(KEY_USER, null)
        return Gson().fromJson(userData, UserEntity::class.java)
    }


}