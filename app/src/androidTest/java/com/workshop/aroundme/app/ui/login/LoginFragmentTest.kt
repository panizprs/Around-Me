package com.workshop.aroundme.app.ui.login


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import com.workshop.aroundme.R
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginFragmentTest{

    @Test
    fun show_error_dialog_when_pass_is_wrong(){

        val scenario = launchFragmentInContainer<LoginFragment>()

//        wrong password
        login("reza", "12")
        onView(withText("Invalid username or password")).check(matches(isDisplayed()))
    }


    @Test
    fun show_error_dialog_when_user_is_wrong(){

        val scenario = launchFragmentInContainer<LoginFragment>()

//        wrong userName
        login("sa", "12")
        onView(withText("Invalid username or password")).check(matches(isDisplayed()))

    }


    private fun login(userName: String, password: String){
        onView(withId(R.id.login)).check(matches(withText(R.string.login)))
        onView(withId(R.id.username)).perform(clearText(),  typeText(userName))
        onView(withId(R.id.password)).perform(clearText(), typeText(password))
        onView(withId(R.id.login)).perform(click())

    }
}

