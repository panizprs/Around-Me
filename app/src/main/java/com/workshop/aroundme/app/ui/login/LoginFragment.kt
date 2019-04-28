package com.workshop.aroundme.app.ui.login

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.ui.home.HomeFragment

class LoginFragment : Fragment(), LoginContract.View {

    private lateinit var usernameEditText: EditText

    private lateinit var passwordEditText: EditText

    private val presenter: LoginContract.Presenter by lazy {
        LoginPresenter(Injector.provideUserRepository(requireContext())).apply {
            view = this@LoginFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameEditText = view.findViewById(R.id.username)
        passwordEditText = view.findViewById(R.id.password)
        view.findViewById<View>(R.id.login).setOnClickListener {
            presenter.onLoginButtonClicked()
        }
    }

    override fun getUsernameValue(): String {
        return usernameEditText.text.toString()
    }

    override fun getPasswordValue(): String {
        return passwordEditText.text.toString()
    }

    override fun showHomeFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.content_frame, HomeFragment())
            ?.commit()
    }

    override fun showMessageToUser(message: Int, title: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok)) { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            .create()
            .show()
    }

}
