package com.workshop.aroundme.app.ui.login

import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.domain.model.User
import com.workshop.aroundme.domain.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class LoginPresenterTest {

    @Test
    fun `onLoginCLicked correct username and password`() {
        val view = mockk<LoginContract.View>(relaxUnitFun = true)
        val userRepository = mockk<UserRepository>(relaxUnitFun = true)

        val loginPresenter = LoginPresenter(view, userRepository)

        val user = User("reza")

        every { view.getUsernameValue() } returns "iman"
        every { view.getPasswordValue() } returns "1234"

        loginPresenter.onLoginButtonClicked()

        verify {
            view.getUsernameValue()
            view.getPasswordValue()
            userRepository.login(user)
            view.showHomeFragment()
        }
    }
}