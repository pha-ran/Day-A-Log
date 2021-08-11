package com.day_a_log.src.login.signup

import com.day_a_log.src.login.signup.models.DuplicatedIdResponse

interface DuplicatedIdView {
    fun onGetDuplicatedIdSuccess(response: DuplicatedIdResponse)
    fun onGetDuplicatedIdFailure(message: String)
}