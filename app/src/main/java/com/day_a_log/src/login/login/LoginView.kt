package com.day_a_log.src.login.login

import com.day_a_log.src.login.login.models.LoginResponse

interface LoginView {
    fun onPostLoginSuccess(response: LoginResponse)
    fun onPostLoginFailure(message: String)
}