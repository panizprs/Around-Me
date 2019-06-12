package com.workshop.aroundme.local.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.workshop.aroundme.data.model.UserEntity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class UserLocalDataSourceImplTest{

    lateinit var sharedPreferences : SharedPreferences
    lateinit var userLocalDataSourceImpl: UserLocalDataSourceImpl

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        sharedPreferences = context.getSharedPreferences("user.data", Context.MODE_PRIVATE)
        sharedPreferences.clear()

        userLocalDataSourceImpl = UserLocalDataSourceImpl(sharedPreferences)
    }


    @Test
    fun return_empty_when_user_is_not_logged_in(){
        assertNull(userLocalDataSourceImpl.getUser())
    }


    @Test
    fun return_user_when_user_is_logged_in(){
        val user = UserEntity("")
        userLocalDataSourceImpl.login(user)
        assertEquals(userLocalDataSourceImpl.getUser(), user)
    }

    @Test
    fun return_empty_after_user_logout(){
        userLocalDataSourceImpl.logout()
        assertNull(userLocalDataSourceImpl.getUser())
    }


    @After
    fun tearDown(){
        sharedPreferences.clear()
    }

    private fun SharedPreferences.clear(){
        val edit = edit()
        all.forEach {(k, _) ->
            edit.remove(k)
        }
        edit.commit()
    }
}