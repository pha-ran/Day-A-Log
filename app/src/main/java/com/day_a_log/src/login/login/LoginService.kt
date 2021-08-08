package com.day_a_log.src.login.login

import com.day_a_log.config.ApplicationClass
import com.day_a_log.src.login.login.models.LoginRequest
import com.day_a_log.src.login.login.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val view: LoginFragment) {
    fun tryPostLogin(postLoginRequest: LoginRequest){
        val loginInterface = ApplicationClass.sRetrofit.create(LoginInterface::class.java)
        loginInterface.postLogin(postLoginRequest).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                view.onPostLoginSuccess(response.body() as LoginResponse)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                view.onPostLoginFailure(t.message ?: "통신 오류")
            }
        })
    }
}
