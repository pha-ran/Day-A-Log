package com.day_a_log.src.add.routine

import com.day_a_log.config.ApplicationClass
import com.day_a_log.src.add.routine.models.AddRoutineRequest
import com.day_a_log.src.add.routine.models.AddRoutineResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddRoutineService(val view: AddRoutineFragment) {
    fun tryPostAddRoutine(addRoutineRequest: AddRoutineRequest){
        val addRoutineInterface = ApplicationClass.sRetrofit.create(AddRoutineInterface::class.java)
        addRoutineInterface.postAddRoutine(addRoutineRequest).enqueue(object : Callback<AddRoutineResponse>{
            override fun onResponse(call: Call<AddRoutineResponse>, response: Response<AddRoutineResponse>) {
                view.onPostAddRoutineSuccess(response.body() as AddRoutineResponse)
            }

            override fun onFailure(call: Call<AddRoutineResponse>, t: Throwable) {
                view.onPostAddRoutineFailure(t.message ?: "통신 오류")
            }
        })
    }
}
