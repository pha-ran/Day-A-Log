package com.day_a_log.src.login.signup

import com.day_a_log.src.login.signup.models.DuplicatedPhoneResponse
import retrofit2.Call
import retrofit2.http.*

interface DuplicatedPhoneInterface {
    @GET("auth/duplicated-phone")
    fun duplicatedPhone(@Query("phone") phone : String)
    : Call<DuplicatedPhoneResponse>
}
