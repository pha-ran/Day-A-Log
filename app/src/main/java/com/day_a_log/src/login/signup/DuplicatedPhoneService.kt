package com.day_a_log.src.login.signup

import com.day_a_log.config.ApplicationClass
import com.day_a_log.src.login.signup.models.DuplicatedPhoneResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DuplicatedPhoneService(val view: SignUpFragment) {
    fun tryGetDuplicatedPhone(phone : String){
        val duplicatedPhoneInterface = ApplicationClass.sRetrofit.create(DuplicatedPhoneInterface::class.java)
        duplicatedPhoneInterface.duplicatedPhone(phone).enqueue(object : Callback<DuplicatedPhoneResponse>{
            override fun onResponse(call: Call<DuplicatedPhoneResponse>, response: Response<DuplicatedPhoneResponse>) {
                view.onGetDuplicatedPhoneSuccess(response.body() as DuplicatedPhoneResponse)
            }

            override fun onFailure(call: Call<DuplicatedPhoneResponse>, t: Throwable) {
                view.onGetDuplicatedPhoneFailure(t.message ?: "통신 오류")
            }
        })
    }
}
