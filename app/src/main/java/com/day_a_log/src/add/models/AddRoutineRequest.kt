package com.day_a_log.src.add.models

data class AddRoutineRequest(
    val type: String,
    val title: String,
    val bgColor: String,
    val contents: String,
    val logArray: List<LogData>
)