package com.day_a_log.src.login.login.models

data class LoginResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: LoginResult
)