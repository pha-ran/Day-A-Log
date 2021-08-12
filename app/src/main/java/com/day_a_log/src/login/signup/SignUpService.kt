package com.day_a_log.src.login.signup

import com.day_a_log.config.ApplicationClass
import com.day_a_log.src.login.signup.models.SignUpRequest
import com.day_a_log.src.login.signup.models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpService(val view: SignUpFragment) {
    fun tryPostSignUp(signUpRequest: SignUpRequest){
        val signUpInterface = ApplicationClass.sRetrofit.create(SignUpInterface::class.java)
        signUpInterface.signUp(signUpRequest).enqueue(object : Callback<SignUpResponse>{
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                view.onPostSignUpSuccess(response.body() as SignUpResponse)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                view.onPostSignUpFailure(t.message ?: "통신 오류")
            }
        })
    }
}
