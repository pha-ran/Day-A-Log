package com.day_a_log.src.login.signup

import com.day_a_log.src.login.signup.models.DuplicatedPhoneResponse

interface DuplicatedPhoneView {
    fun onGetDuplicatedPhoneSuccess(response: DuplicatedPhoneResponse)
    fun onGetDuplicatedPhoneFailure(message: String)
}