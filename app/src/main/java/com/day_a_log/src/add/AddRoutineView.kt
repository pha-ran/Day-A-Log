package com.day_a_log.src.add

import com.day_a_log.src.add.models.AddRoutineResponse

interface AddRoutineView {
    fun onPostAddRoutineSuccess(response: AddRoutineResponse)
    fun onPostAddRoutineFailure(message: String)
}