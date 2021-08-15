package com.day_a_log.src.login.login.models

data class KakaoRequest(
    val client_id: String,
    val redirect_uri: String,
    val response_type: String,
    val state: String?,
    val prompt: String?
)