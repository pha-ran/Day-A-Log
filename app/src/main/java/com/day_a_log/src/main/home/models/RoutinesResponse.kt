package com.day_a_log.src.main.home.models

data class RoutinesResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<RoutinesResult>
)