package com.day_a_log.src.login.login.models

data class KakaoResponse(
    val code: String,
    val state: String?,
    val error: String?,
    val error_description: String?
)