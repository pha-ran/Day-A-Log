package com.day_a_log.src.login.signup

import com.day_a_log.src.login.signup.models.SignUpResponse

interface SignUpView {
    fun onPostSignUpSuccess(response: SignUpResponse)
    fun onPostSignUpFailure(message: String)
}