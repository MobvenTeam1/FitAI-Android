package com.mobven.fitai.data.model.dto

data class UpdateWorkoutDto(
    val day: String,
    val exercise: String,
    val newExercise: String,
    val newSets: String
)