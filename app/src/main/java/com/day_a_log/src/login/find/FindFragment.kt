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

        var page = 0

        binding.ivPage.setOnClickListener {
            if (page == 0) {
                binding.ivPage.setImageResource(R.drawable.ic_find_page_2)
                binding.linearBg.setBackgroundResource(R.drawable.bg_fragment_find_page_2)
                binding.tvHint.text = "문자로 찾기"
                binding.etvSend.hint = "Phone number"
                binding.linearKey.visibility = View.INVISIBLE
                binding.btnSend.visibility = View.VISIBLE
                page = 1
            }
            else {
                binding.ivPage.setImageResource(R.drawable.ic_find_page_1)
                binding.linearBg.setBackgroundResource(R.drawable.bg_fragment_find_page_1)
                binding.tvHint.text = "이메일로 찾기"
                binding.etvSend.hint = "Email"
                binding.linearKey.visibility = View.INVISIBLE
                binding.btnSend.visibility = View.VISIBLE
                page = 0
            }
        }

        binding.ivExit.setOnClickListener {
            (activity as LoginActivity).replaceFragment(LoginFragment())
        }

        binding.btnSend.setOnClickListener {
            binding.linearKey.visibility = View.VISIBLE
            binding.btnSend.visibility = View.GONE
        }
    }
}