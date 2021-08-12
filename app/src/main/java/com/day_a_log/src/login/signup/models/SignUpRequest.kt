package com.day_a_log.src.login.signup.models

data class SignUpRequest(
    val name: String,
    val phone: String,
    val userId: String,
    val userPw: String
)