package com.mobven.fitai.presentation.add.viewmodel

import com.mobven.fitai.presentation.add.food.adapter.FoodModel
import com.mobven.fitai.presentation.add.training.adapter.TrainingModel

sealed class AddOnAction {
    data object GetTrainingItems : AddOnAction()
    data object GetFoodItems : AddOnAction()
    data class AddSelectedTraining(val training: TrainingModel) : AddOnAction()
    data class AddSelectedFood(val food: FoodModel) : AddOnAction()
    data class RemoveSelectedFood(val food: FoodModel) : AddOnAction()
    data class RemoveSelectedTraining(val training: TrainingModel) : AddOnAction()
}