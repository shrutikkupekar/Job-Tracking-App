package com.example.jobtracker.data.model

import java.util.Date

data class Interview(
    val id: String = "",
    val jobApplicationId: String = "",
    val userId: String = "",
    val interviewType: String = "",
    val interviewDate: Date = Date(),
    val status: String = "Scheduled",
    val notes: String = ""
)
