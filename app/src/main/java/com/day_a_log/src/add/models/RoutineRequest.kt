package com.day_a_log.src.add.models

data class RoutineRequest(
    val authorIdx: Int,
    val type: String,
    val title: String,
    val bgColor: String,
    val contents: String,
    val logArray: ArrayList<LogData>
)