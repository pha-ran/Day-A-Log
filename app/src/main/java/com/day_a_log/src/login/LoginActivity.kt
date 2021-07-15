package com.day_a_log.src.login

import android.os.Bundle
import com.day_a_log.R
import com.day_a_log.config.BaseActivity
import com.day_a_log.databinding.ActivityLoginBinding
import com.day_a_log.src.login.login.LoginFragment

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.fl, LoginFragment()).commitAllowingStateLoss()
    }
}