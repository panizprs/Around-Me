package com.workshop.aroundme.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.aroundme.R
import com.workshop.aroundme.domain.interactor.user.LoginUserUseCase
import com.workshop.aroundme.domain.model.User

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : ViewModel() {


    private val _showHomeFragment = MutableLiveData<Unit>()
    val showHomeFragment : LiveData<Unit> = _showHomeFragment

    private val _showErrorMessageToUser = MutableLiveData<Pair<Int, Int>>()
    val showErrorMessageToUser : LiveData<Pair<Int, Int>> = _showErrorMessageToUser

    fun loginUser(userName : String, password : String) {
        if (userName.isNotEmpty() && userName == "reza"
            && password.isNotEmpty() && password == "1234"
        ) {
            val user = User(userName)
            loginUserUseCase.execute(user)
            _showHomeFragment.value = Unit

        } else {
            _showErrorMessageToUser.value = R.string.invalid_user_or_pass to R.string.error
        }

    }
}