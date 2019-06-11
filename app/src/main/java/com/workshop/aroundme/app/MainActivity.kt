package com.workshop.aroundme.app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.home.HomeFragment
import com.workshop.aroundme.app.ui.login.LoginFragment
import com.workshop.aroundme.app.ui.starred.StarredFragment
import com.workshop.aroundme.domain.interactor.user.IsLoggedInUserUseCase
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var isLoginUserUseCase: IsLoggedInUserUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        if (isLoginUserUseCase.isLoggedIn()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, HomeFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, LoginFragment())
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.starredPlaces -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_frame, StarredFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
