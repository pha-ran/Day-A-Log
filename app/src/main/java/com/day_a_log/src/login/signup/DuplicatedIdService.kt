package com.day_a_log.src.login.signup

import com.day_a_log.config.ApplicationClass
import com.day_a_log.src.login.signup.models.DuplicatedIdResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DuplicatedIdService(val view: SignUpFragment) {
    fun tryGetDuplicatedId(id : String){
        val duplicatedIdInterface = ApplicationClass.sRetrofit.create(DuplicatedIdInterface::class.java)
        duplicatedIdInterface.duplicatedId(id).enqueue(object : Callback<DuplicatedIdResponse>{
            override fun onResponse(call: Call<DuplicatedIdResponse>, response: Response<DuplicatedIdResponse>) {
                view.onGetDuplicatedIdSuccess(response.body() as DuplicatedIdResponse)
            }

            override fun onFailure(call: Call<DuplicatedIdResponse>, t: Throwable) {
                view.onGetDuplicatedIdFailure(t.message ?: "통신 오류")
            }
        })
    }
}
