package com.example.jobtracker.data.model

import java.util.Date

data class JobApplication(
    val id: String = "",
    val userId: String = "",
    val companyName: String = "",
    val jobTitle: String = "",
    val location: String = "",
    val status: String = "Applied",
    val appliedDate: Date = Date(),
    val jobLink: String = "",
    val notes: String = ""
)
