package com.day_a_log.src.login.signup

import com.day_a_log.config.ApplicationClass
import com.day_a_log.src.login.signup.models.DuplicatedEmailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DuplicatedEmailService(val view: SignUpFragment) {
    fun tryGetDuplicatedEmail(email : String){
        val duplicatedEmailInterface = ApplicationClass.sRetrofit.create(DuplicatedEmailInterface::class.java)
        duplicatedEmailInterface.duplicatedEmail(email).enqueue(object : Callback<DuplicatedEmailResponse>{
            override fun onResponse(call: Call<DuplicatedEmailResponse>, response: Response<DuplicatedEmailResponse>) {
                view.onGetDuplicatedEmailSuccess(response.body() as DuplicatedEmailResponse)
            }

            override fun onFailure(call: Call<DuplicatedEmailResponse>, t: Throwable) {
                view.onGetDuplicatedEmailFailure(t.message ?: "통신 오류")
            }
        })
    }
}
