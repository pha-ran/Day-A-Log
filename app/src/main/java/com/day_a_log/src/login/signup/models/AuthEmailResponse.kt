package com.day_a_log.src.login.signup.models

data class AuthEmailResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: AuthEmailResult
)