package com.day_a_log.src.splash

import com.day_a_log.src.splash.models.JwtResponse

interface JwtView {
    fun onGetJwtSuccess(response: JwtResponse)
    fun onGetJwtFailure(message: String)
}