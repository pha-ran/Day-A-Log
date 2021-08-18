package com.day_a_log.src.login.signup

import com.day_a_log.src.login.signup.models.DuplicatedEmailResponse

interface DuplicatedEmailView {
    fun onGetDuplicatedEmailSuccess(response: DuplicatedEmailResponse)
    fun onGetDuplicatedEmailFailure(message: String)
}