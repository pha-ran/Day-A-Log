package com.day_a_log.src.login.login

import android.os.Bundle
import android.view.View
import com.day_a_log.R
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

//        showLoadingDialog(requireContext())
//        val loginRequest = LoginRequest(
//            email = "engus0927@gmail.com",
//            pwd = "qqqq1111"
//        )
//        LoginService(this).tryPostLogin(loginRequest)

        binding.tvSignUp.setOnClickListener {
            (activity as LoginActivity).replaceFragment(SignUpFragment())
        }

        binding.tvFind.setOnClickListener {
            (activity as LoginActivity).replaceFragment(FindFragment())
        }

        binding.btnLogin.setOnClickListener {
            (activity as LoginActivity).login()
        }
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
        dismissLoadingDialog()
        showCustomToast(response.message)
    }

    override fun onPostLoginFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}