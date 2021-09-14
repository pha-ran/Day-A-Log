package com.day_a_log.src.add.routine

import com.day_a_log.src.add.routine.models.AddRoutineResponse

interface AddRoutineView {
    fun onPostAddRoutineSuccess(response: AddRoutineResponse)
    fun onPostAddRoutineFailure(message: String)
}