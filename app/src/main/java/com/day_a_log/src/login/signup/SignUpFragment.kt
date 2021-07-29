package com.day_a_log.src.login.signup

import android.os.Bundle
import android.view.View
import com.day_a_log.R
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentSignUpBinding
import com.day_a_log.src.login.LoginActivity
import com.day_a_log.src.login.login.LoginFragment

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::bind, R.layout.fragment_sign_up) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLogin.setOnClickListener {
            (activity as LoginActivity).replaceFragment(LoginFragment())
        }

        binding.btnSend.setOnClickListener {
            binding.btnSend.visibility = View.GONE
            binding.linearCode.visibility = View.VISIBLE
            binding.etName.isEnabled = false
            binding.etPhoneNumber.isEnabled = false
        }

        binding.btnCode.setOnClickListener {
            binding.linearKey.visibility = View.GONE
            binding.linearIdPassword.visibility = View.VISIBLE
        }
    }
}