package com.day_a_log.src.main.home

import com.day_a_log.src.main.home.models.RoutinesResponse
import retrofit2.Call
import retrofit2.http.*

interface RoutinesInterface {
    @GET("mypage/{userIdx}/routines/id")
    fun routines(
        @Path("userIdx") userIdx : Int,
        @Query("id") id : String
    )
    : Call<RoutinesResponse>
}
