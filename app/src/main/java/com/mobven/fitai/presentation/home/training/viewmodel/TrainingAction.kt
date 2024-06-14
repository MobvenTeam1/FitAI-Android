package com.mobven.fitai.presentation.home.training.viewmodel

import com.mobven.fitai.util.enums.TrainingSelectorItem

sealed class TrainingAction {
    data class SaveWorkoutDetails(val authToken: String) :
        TrainingAction()
    data class GetTrainingSelectorItem(val workoutDetails: TrainingSelectorItem) :
        TrainingAction()
}