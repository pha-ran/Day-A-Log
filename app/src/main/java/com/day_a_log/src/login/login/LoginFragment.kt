package com.day_a_log.src.login.login

import android.os.Bundle
import android.view.View
import com.day_a_log.R
import com.day_a_log.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.day_a_log.config.ApplicationClass.Companion.sSharedPreferences
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentLoginBinding
import com.day_a_log.src.login.LoginActivity
import com.day_a_log.src.login.find.FindFragment
import com.day_a_log.src.login.login.models.LoginRequest
import com.day_a_log.src.login.login.models.LoginResponse
import com.day_a_log.src.login.signup.SignUpFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login),
    LoginView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSignUp.setOnClickListener {
            (activity as LoginActivity).replaceFragment(SignUpFragment())
        }

        binding.tvFind.setOnClickListener {
            (activity as LoginActivity).replaceFragment(FindFragment())
        }

        binding.btnLogin.setOnClickListener {
            showLoadingDialog(requireContext())
            LoginService(this).tryPostLogin(LoginRequest(
                userId = binding.etId.text.toString(),
                userPw = binding.etPassword.text.toString()
            ))
        }
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
        dismissLoadingDialog()
        showCustomToast(response.message)

        if (response.code == 1000) {
            sSharedPreferences.edit().putString(X_ACCESS_TOKEN, response.result.jwt).apply()
            (activity as LoginActivity).login()
        }
    }

    override fun onPostLoginFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}