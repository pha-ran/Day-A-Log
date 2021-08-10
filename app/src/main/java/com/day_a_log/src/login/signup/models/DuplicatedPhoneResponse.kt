package com.day_a_log.src.login.signup.models

data class DuplicatedPhoneResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String
)