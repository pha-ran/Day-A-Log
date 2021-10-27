package com.day_a_log.src.main.home

import com.day_a_log.config.ApplicationClass
import com.day_a_log.src.main.home.models.RoutinesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoutinesService(val view: HomeFragment) {
    fun tryGetRoutines(){
        val routinesInterface = ApplicationClass.sRetrofit.create(RoutinesInterface::class.java)
        routinesInterface.routines(
            ApplicationClass.sSharedPreferences.getInt(ApplicationClass.User_Idx, -1),
            "test0" //ToDo 테스트용 아이디 -> 유저 아이디로 변경
        ).enqueue(object : Callback<RoutinesResponse>{
            override fun onResponse(call: Call<RoutinesResponse>, response: Response<RoutinesResponse>) {
                view.onGetRoutinesSuccess(response.body() as RoutinesResponse)
            }

            override fun onFailure(call: Call<RoutinesResponse>, t: Throwable) {
                view.onGetRoutinesFailure(t.message ?: "통신 오류")
            }
        })
    }
}
