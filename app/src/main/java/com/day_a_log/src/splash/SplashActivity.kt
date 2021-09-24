package com.day_a_log.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.day_a_log.config.ApplicationClass
import com.day_a_log.config.BaseActivity
import com.day_a_log.databinding.ActivitySplashBinding
import com.day_a_log.src.login.LoginActivity
import com.day_a_log.src.main.MainActivity
import com.day_a_log.src.splash.models.JwtResponse

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate),
    JwtView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLoadingDialog(this)
        JwtService(this).tryJwt()
    }

    override fun onGetJwtSuccess(response: JwtResponse) {
        dismissLoadingDialog()
        showCustomToast(response.message)

        if (response.code == 1001) {
            showCustomToast("유저 id : "+response.result.userIdx.toString())
            ApplicationClass.sSharedPreferences.edit().putInt(ApplicationClass.User_Idx, response.result.userIdx).apply()
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 1500)
        }
        else {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }, 1500)
        }
    }

    override fun onGetJwtFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500)
    }
}