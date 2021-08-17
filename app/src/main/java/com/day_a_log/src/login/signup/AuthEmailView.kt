package com.day_a_log.src.login.signup

import com.day_a_log.src.login.signup.models.AuthEmailResponse

interface AuthEmailView {
    fun onPostAuthEmailSuccess(response: AuthEmailResponse)
    fun onPostAuthEmailFailure(message: String)
}