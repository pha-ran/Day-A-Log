package com.day_a_log.src.splash

import com.day_a_log.config.ApplicationClass
import com.day_a_log.src.splash.models.JwtResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JwtService(val view: SplashActivity) {
    fun tryJwt(){
        val jwtInterface = ApplicationClass.sRetrofit.create(JwtInterface::class.java)
        jwtInterface.jwt().enqueue(object : Callback<JwtResponse>{
            override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                view.onGetJwtSuccess(response.body() as JwtResponse)
            }

            override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                view.onGetJwtFailure(t.message ?: "통신 오류")
            }
        })
    }
}
