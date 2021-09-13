package com.day_a_log.src.add.models

data class LogData(
    val time: String,
    val location: String,
    val subject: String,
    val contents: String,
    val imgUrl: String,
    val type: String,
    val packageIdx: Int
)