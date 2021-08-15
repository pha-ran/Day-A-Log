package com.day_a_log.src.login.login

import com.day_a_log.config.ApplicationClass
import com.day_a_log.src.login.login.models.KakaoRequest
import com.day_a_log.src.login.login.models.KakaoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoService(val view: LoginFragment) {
    fun tryGetKakaoLogin(kakaoRequest: KakaoRequest){
        val kakaoInterface = ApplicationClass.kRetrofit.create(KakaoInterface::class.java)
        kakaoInterface.getKakaoLogin(
            kakaoRequest.client_id,
            kakaoRequest.redirect_uri,
            kakaoRequest.response_type,
            kakaoRequest.state,
            kakaoRequest.prompt
        ).enqueue(object : Callback<KakaoResponse>{
            override fun onResponse(call: Call<KakaoResponse>, response: Response<KakaoResponse>) {
                view.onGetKakaoLoginSuccess(response.body() as KakaoResponse)
            }

            override fun onFailure(call: Call<KakaoResponse>, t: Throwable) {
                view.onGetKakaoLoginFailure(t.message ?: "통신 오류")
            }
        })
    }
}
