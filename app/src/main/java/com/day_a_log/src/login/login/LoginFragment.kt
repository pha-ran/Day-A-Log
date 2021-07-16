package com.day_a_log.src.login.login

import android.os.Bundle
import android.view.View
import com.day_a_log.R
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentLoginBinding
import com.day_a_log.src.login.LoginActivity
import com.day_a_log.src.login.find.FindFragment
import com.day_a_log.src.login.signup.SignUpFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSignUp.setOnClickListener {
            (activity as LoginActivity).replaceFragment(SignUpFragment())
        }

        binding.tvFind.setOnClickListener {
            (activity as LoginActivity).replaceFragment(FindFragment())
        }
    }
}