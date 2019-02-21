package com.workshop.aroundme.local.datasource

import android.content.SharedPreferences

class UserLocalDataSource(private val sharedPreferences: SharedPreferences) {

    fun login() {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, true).commit()
    }

    fun logout() {
        sharedPreferences.edit().remove(KEY_IS_LOGGED_IN).commit()
    }

    fun isLoggedIn() = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)


    companion object {
        const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}