package com.workshop.aroundme.app.ui.login


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.home.HomeFragment

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @SuppressLint("ApplySharedPref")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        view.findViewById<View>(R.id.login).setOnClickListener {

            if (usernameEditText.text.isNotEmpty() && usernameEditText.text.toString() == "reza"
                && passwordEditText.text.isNotEmpty() && passwordEditText.text.toString() == "1234"
            ) {

                val sharedPreferences = context?.getSharedPreferences("user.data", Context.MODE_PRIVATE)
                sharedPreferences
                    ?.edit()
                    ?.putBoolean(KEY_IS_LOGGED_IN, true)
                    ?.commit()

                fragmentManager?.beginTransaction()
                    ?.replace(R.id.content_frame, HomeFragment())
                    ?.commit()
            } else {
                AlertDialog.Builder(view.context)
                    .setTitle(getString(R.string.error))
                    .setMessage(getString(R.string.invalid_user_or_pass))
                    .setPositiveButton(getString(R.string.ok)) { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()
                    }
                    .create()
                    .show()

            }
        }
    }

    companion object {
        const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}
