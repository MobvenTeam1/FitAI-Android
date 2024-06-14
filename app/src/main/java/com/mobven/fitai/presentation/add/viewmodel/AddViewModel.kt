package com.mobven.fitai.presentation.add.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobven.fitai.R
import com.mobven.fitai.infrastructure.string_resource.StringResourceProvider
import com.mobven.fitai.presentation.add.adapter.AddItemModel
import com.mobven.fitai.presentation.add.food.adapter.FoodModel
import com.mobven.fitai.presentation.add.training.adapter.TrainingModel
import com.mobven.fitai.util.enums.SelectItemType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val stringResource: StringResourceProvider
) : ViewModel() {

    private val _uiState = MutableLiveData(AddUiState())
    val uiState: LiveData<AddUiState> = _uiState

    fun onAction(action: AddOnAction) {
        when (action) {
            AddOnAction.GetTrainingItems -> getTrainingItemList()
            AddOnAction.GetFoodItems -> getFoodItemList()
            is AddOnAction.AddSelectedFood -> addFoodItem(action.food)
            is AddOnAction.AddSelectedTraining -> addTrainingItem(action.training)
            is AddOnAction.RemoveSelectedFood -> removeFoodItem(action.food)
            is AddOnAction.RemoveSelectedTraining -> removeTrainingItem(action.training)
        }
    }

    private fun getTrainingItemList() {
        _uiState.value = _uiState.value?.copy(
            trainingItemList =
            listOf(
                AddItemModel(
                    stringResource.getString(R.string.walking),
                    R.drawable.walking,
                    stringResource.getString(R.string.low_temp_one_hour_65_kcal),
                    SelectItemType.TRAINING
                ),
                AddItemModel(
                    stringResource.getString(R.string.running),
                    R.drawable.running,
                    stringResource.getString(R.string.one_hour_178_kcal),
                    SelectItemType.TRAINING
                ),
                AddItemModel(
                    stringResource.getString(R.string.biceyle),
                    R.drawable.biceyle,
                    stringResource.getString(R.string.one_hour_178_kcal),
                    SelectItemType.TRAINING
                ),
                AddItemModel(
                    stringResource.getString(R.string.swimming),
                    R.drawable.swimming,
                    stringResource.getString(R.string.one_hour_178_kcal),
                    SelectItemType.TRAINING
                ),
                AddItemModel(
                    stringResource.getString(R.string.tennis),
                    R.drawable.tennis,
                    stringResource.getString(R.string.one_hour_280_kcal),
                    SelectItemType.TRAINING
                )
            ).toMutableList()
        )
    }

    private fun getFoodItemList() {
        _uiState.value = _uiState.value?.copy(
            foodItemList =
            listOf(
                AddItemModel(
                    stringResource.getString(R.string.egg),
                    R.drawable.food_egg,
                    stringResource.getString(R.string.two_piece_180_kcal),
                    SelectItemType.FOOD
                ),
                AddItemModel(
                    stringResource.getString(R.string.avocado),
                    R.drawable.food_avocado,
                    stringResource.getString(R.string.one_half_220_kcal),
                    SelectItemType.FOOD
                ),
                AddItemModel(
                    stringResource.getString(R.string.tomato),
                    R.drawable.food_tomato,
                    stringResource.getString(R.string.one_piece_40_kcal),
                    SelectItemType.FOOD
                ),
                AddItemModel(
                    stringResource.getString(R.string.bread),
                    R.drawable.food_bread,
                    stringResource.getString(R.string.one_slice_75_kcal),
                    SelectItemType.FOOD
                )
            ).toMutableList()
        )
    }

    private fun addTrainingItem(trainingModel: TrainingModel) {
        _uiState.value = _uiState.value?.copy(
            trainingSelectedList = _uiState.value?.trainingSelectedList?.toMutableList()?.apply {
                val existingTraining = find { it.name == trainingModel.name }
                if (existingTraining != null) {
                    val index = indexOf(existingTraining)
                    val updatedFood = existingTraining.copy(
                        minute = (existingTraining.minute.toInt() + trainingModel.minute.toInt()).toString()
                    )
                    set(index, updatedFood)
                } else {
                    add(trainingModel)
                }
            } ?: mutableListOf()
        )
    }

    private fun removeTrainingItem(trainingModel: TrainingModel) {
        _uiState.value = _uiState.value?.copy(
            trainingSelectedList = _uiState.value?.trainingSelectedList?.apply {
                remove(trainingModel)
            } ?: mutableListOf()
        )
    }

    private fun addFoodItem(foodModel: FoodModel) {
        _uiState.value = _uiState.value?.copy(
            foodSelectedList = _uiState.value?.foodSelectedList?.toMutableList()?.apply {
                val existingFood = find { it.name == foodModel.name }
                if (existingFood != null) {
                    val index = indexOf(existingFood)
                    val updatedFood = existingFood.copy(count = existingFood.count + 1)
                    set(index, updatedFood)
                } else {
                    add(foodModel)
                }
            } ?: mutableListOf()
        )
    }

    private fun removeFoodItem(foodModel: FoodModel) {
        _uiState.value = _uiState.value?.copy(
            foodSelectedList = _uiState.value?.foodSelectedList?.apply {
                if (contains(foodModel)) {
                    val index = indexOf(foodModel)
                    val food = get(index)
                    food.count -= 1
                    set(index, food)
                }
            } ?: mutableListOf()
        )
    }

}

data class AddUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val trainingItemList: MutableList<AddItemModel> = mutableListOf(),
    val foodItemList: MutableList<AddItemModel> = mutableListOf(),
    val trainingSelectedList: MutableList<TrainingModel> = mutableListOf(),
    val foodSelectedList: MutableList<FoodModel> = mutableListOf(),
)