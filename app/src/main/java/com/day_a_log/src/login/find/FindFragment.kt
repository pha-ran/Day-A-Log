package com.day_a_log.src.login.find

import android.os.Bundle
import android.view.View
import com.day_a_log.R
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentFindBinding
import com.day_a_log.src.login.LoginActivity
import com.day_a_log.src.login.login.LoginFragment

class FindFragment : BaseFragment<FragmentFindBinding>(FragmentFindBinding::bind, R.layout.fragment_find) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivExit.setOnClickListener {
            (activity as LoginActivity).replaceFragment(LoginFragment())
        }

        binding.btnSend.setOnClickListener {
            binding.linearCode.visibility = View.VISIBLE
            binding.btnSend.visibility = View.GONE
            binding.etEmail.isEnabled = false
        }
    }
}