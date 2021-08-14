package com.day_a_log.src.splash

import com.day_a_log.src.splash.models.JwtResponse
import retrofit2.Call
import retrofit2.http.*

interface JwtInterface {
    @GET("auth/jwt")
    fun jwt()
    : Call<JwtResponse>
}
