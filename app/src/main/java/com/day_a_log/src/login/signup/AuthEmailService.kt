package com.day_a_log.src.login.signup

import com.day_a_log.config.ApplicationClass
import com.day_a_log.src.login.signup.models.AuthEmailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthEmailService(val view: SignUpFragment) {
    fun tryPostAuthEmail(email : String){
        val authEmailInterface = ApplicationClass.sRetrofit.create(AuthEmailInterface::class.java)
        authEmailInterface.authEmail(email).enqueue(object : Callback<AuthEmailResponse>{
            override fun onResponse(call: Call<AuthEmailResponse>, response: Response<AuthEmailResponse>) {
                view.onPostAuthEmailSuccess(response.body() as AuthEmailResponse)
            }

            override fun onFailure(call: Call<AuthEmailResponse>, t: Throwable) {
                view.onPostAuthEmailFailure(t.message ?: "통신 오류")
            }
        })
    }
}
