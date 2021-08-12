package com.day_a_log.src.login.signup

import com.day_a_log.src.login.signup.models.SignUpRequest
import com.day_a_log.src.login.signup.models.SignUpResponse
import retrofit2.Call
import retrofit2.http.*

interface SignUpInterface {
    @POST("/users")
    fun signUp(@Body params: SignUpRequest)
    : Call<SignUpResponse>
}
