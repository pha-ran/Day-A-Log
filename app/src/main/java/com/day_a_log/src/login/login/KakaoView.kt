package com.day_a_log.src.login.login

import com.day_a_log.src.login.login.models.KakaoResponse

interface KakaoView {
    fun onGetKakaoLoginSuccess(response: KakaoResponse)
    fun onGetKakaoLoginFailure(message: String)
}