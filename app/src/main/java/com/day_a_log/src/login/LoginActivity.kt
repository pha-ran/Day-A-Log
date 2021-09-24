package com.day_a_log.src.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.day_a_log.R
import com.day_a_log.config.BaseActivity
import com.day_a_log.databinding.ActivityLoginBinding
import com.day_a_log.src.login.login.LoginFragment
import com.day_a_log.src.splash.SplashActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragment(LoginFragment())
    }

    fun replaceFragment(F : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl, F).commitAllowingStateLoss()
    }

    fun login() {
        startActivity(Intent(this, SplashActivity::class.java))
        finish()
    }
}