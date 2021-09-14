package com.day_a_log.src.add.routine

import com.day_a_log.src.add.routine.models.AddRoutineRequest
import com.day_a_log.src.add.routine.models.AddRoutineResponse
import retrofit2.Call
import retrofit2.http.*

interface AddRoutineInterface {
    @POST("routines")
    fun postAddRoutine(@Body params: AddRoutineRequest)
    : Call<AddRoutineResponse>
}
