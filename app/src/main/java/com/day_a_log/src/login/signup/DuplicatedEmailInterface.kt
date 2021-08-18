package com.day_a_log.src.login.signup

import com.day_a_log.src.login.signup.models.DuplicatedEmailResponse
import retrofit2.Call
import retrofit2.http.*

interface DuplicatedEmailInterface {
    @GET("auth/duplicated-email")
    fun duplicatedEmail(@Query("email") email : String)
    : Call<DuplicatedEmailResponse>
}
