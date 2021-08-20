package com.day_a_log.src.login.signup

import com.day_a_log.src.login.signup.models.AuthEmailResponse
import retrofit2.Call
import retrofit2.http.*

interface AuthEmailInterface {
    @POST("auth/email")
    fun authEmail(@Query("email") email : String)
    : Call<AuthEmailResponse>
}
