package com.workshop.aroundme.app.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.workshop.aroundme.domain.interactor.user.LoginUserUseCase
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(private val loginUserUseCase: LoginUserUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginUserUseCase) as T
    }

}