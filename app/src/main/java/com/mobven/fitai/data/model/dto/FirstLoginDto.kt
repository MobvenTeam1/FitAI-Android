package com.mobven.fitai.data.model.dto

data class FirstLoginDto(
    val dateOfBirth: String,
    val firstWeight: Double,
    val gender: String,
    val goals: String,
    val height: Double,
    val targetWeight: Double
)