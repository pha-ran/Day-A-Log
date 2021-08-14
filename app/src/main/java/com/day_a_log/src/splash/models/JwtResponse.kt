package com.day_a_log.src.splash.models

data class JwtResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: JwtResult
)