package com.day_a_log.src.login.login

import com.day_a_log.src.login.login.models.LoginRequest
import com.day_a_log.src.login.login.models.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface LoginInterface {
    @POST("auth/login")
    fun postLogin(@Body params: LoginRequest)
    : Call<LoginResponse>
}
