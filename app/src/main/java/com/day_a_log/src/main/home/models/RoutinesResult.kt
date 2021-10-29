package com.day_a_log.src.main.home.models

data class RoutinesResult(
    val bgColor: String,
    val idx: Int,
    val imgUrl: String,
    val likes: Any,
    val logArray: List<RoutinesLogArray>,
    val status: String,
    val title: String,
    val type: String,
    val updated: Int,
    val updatedAgo: String,
    val userId: String
)