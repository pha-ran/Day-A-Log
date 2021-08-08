package com.day_a_log.src.login.login.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val email: String,
    val pwd: String
)