package com.day_a_log.src.main.home

import com.day_a_log.src.main.home.models.RoutinesResponse

interface RoutinesView {
    fun onGetRoutinesSuccess(response: RoutinesResponse)
    fun onGetRoutinesFailure(message: String)
}