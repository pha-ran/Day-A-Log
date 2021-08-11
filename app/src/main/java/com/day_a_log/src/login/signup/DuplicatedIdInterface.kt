package com.day_a_log.src.login.signup

import com.day_a_log.src.login.signup.models.DuplicatedIdResponse
import retrofit2.Call
import retrofit2.http.*

interface DuplicatedIdInterface {
    @GET("auth/duplicated-id")
    fun duplicatedId(@Query("id") id : String)
    : Call<DuplicatedIdResponse>
}
