package com.mobven.fitai.presentation.home.training.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobven.fitai.R
import com.mobven.fitai.common.ResponseState
import com.mobven.fitai.data.model.dto.WorkoutDetailsDto
import com.mobven.fitai.domain.usecase.SaveWorkoutDetailsUseCase
import com.mobven.fitai.infrastructure.string_resource.StringResourceProvider
import com.mobven.fitai.presentation.login.sign_up.model.ListSelectorItem
import com.mobven.fitai.util.enums.SignUpSelectorType
import com.mobven.fitai.util.enums.TrainingSelectorItem
import com.mobven.fitai.util.enums.TrainingSelectorItem.PREFERRED_SPORT
import com.mobven.fitai.util.enums.TrainingSelectorItem.SPORT_BODY
import com.mobven.fitai.util.enums.TrainingSelectorItem.SPORT_OFTEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val saveWorkoutDetailsUseCase: SaveWorkoutDetailsUseCase,
    private val stringRes: StringResourceProvider,
) : ViewModel() {

    private val _trainingUiState = MutableLiveData(TrainingUiState())
    val trainingUiState: LiveData<TrainingUiState> = _trainingUiState

    fun onAction(action: TrainingAction) {
        when (action) {
            is TrainingAction.SaveWorkoutDetails -> {
                saveWorkoutDetails(action.authToken)
            }

            is TrainingAction.GetTrainingSelectorItem -> {
                getTrainingCategoryList(action.workoutDetails)
            }
        }
    }

    val workoutDetails = WorkoutDetailsDto(
        focusAreas = "",
        healthProblem = "",
        preferredActivities = "",
        workoutFrequency = ""
    )

    var trainingSelectorItem: List<ListSelectorItem> = emptyList()

    private fun saveWorkoutDetails(authToken: String) {
        viewModelScope.launch {
            saveWorkoutDetailsUseCase(authToken, workoutDetails).collect {
                when (it) {
                    is ResponseState.Loading -> {
                        _trainingUiState.value = _trainingUiState.value?.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _trainingUiState.value = _trainingUiState.value?.copy(
                            isLoading = false
                        )
                    }

                    is ResponseState.Error -> {
                        _trainingUiState.value = _trainingUiState.value?.copy(
                            isError = true,
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                }
            }
        }
    }

    private fun getTrainingCategoryList(trainingSelectorType: TrainingSelectorItem) {
        this.trainingSelectorItem = when (trainingSelectorType) {
                PREFERRED_SPORT ->
                    listOf(
                        ListSelectorItem(
                            title = stringRes.getString(R.string.all),
                            isSelected = false,
                            type = SignUpSelectorType.CHECKBOX,
                            image = R.drawable.all_select,
                        ),
                        ListSelectorItem(
                            title = stringRes.getString(R.string.yoga),
                            isSelected = false,
                            type = SignUpSelectorType.CHECKBOX,
                            image = R.drawable.yoga,
                        ),
                        ListSelectorItem(
                            title = stringRes.getString(R.string.fitness),
                            isSelected = false,
                            type = SignUpSelectorType.CHECKBOX,
                            image = R.drawable.fitness,
                        ),
                        ListSelectorItem(
                            title = stringRes.getString(R.string.running),
                            isSelected = false,
                            type = SignUpSelectorType.CHECKBOX,
                            image = R.drawable.running,
                        ),
                        ListSelectorItem(
                            title = stringRes.getString(R.string.walking),
                            isSelected = false,
                            type = SignUpSelectorType.CHECKBOX,
                            image = R.drawable.walking,
                        )
                    ).reversed()

                SPORT_BODY ->
                    listOf(
                        ListSelectorItem(
                            title = stringRes.getString(R.string.all),
                            isSelected = false,
                            type = SignUpSelectorType.CHECKBOX,
                            image = R.drawable.all_select,
                        ),
                        ListSelectorItem(
                            title = stringRes.getString(R.string.shoulder_arms),
                            isSelected = false,
                            type = SignUpSelectorType.CHECKBOX,
                            image = R.drawable.arms,
                        ),
                        ListSelectorItem(
                            title = stringRes.getString(R.string.chest),
                            isSelected = false,
                            type = SignUpSelectorType.CHECKBOX,
                            image = R.drawable.chest,
                        ),
                        ListSelectorItem(
                            title = stringRes.getString(R.string.belly_back),
                            isSelected = false,
                            type = SignUpSelectorType.CHECKBOX,
                            image = R.drawable.belly,
                        ),
                        ListSelectorItem(
                            title = stringRes.getString(R.string.hip),
                            isSelected = false,
                            type = SignUpSelectorType.CHECKBOX,
                            image = R.drawable.hips,
                        ),
                        ListSelectorItem(
                            title = stringRes.getString(R.string.leg),
                            isSelected = false,
                            type = SignUpSelectorType.CHECKBOX,
                            image = R.drawable.legs,
                        ),
                    ).reversed()

                SPORT_OFTEN ->
                    listOf(
                        ListSelectorItem(
                            title = stringRes.getString(R.string.as_much_as_offered),
                            isSelected = false,
                            type = SignUpSelectorType.RADIO,
                        ),
                        ListSelectorItem(
                            title = stringRes.getString(R.string.on_two_times_week),
                            isSelected = false,
                            type = SignUpSelectorType.RADIO,
                        ),
                        ListSelectorItem(
                            title = stringRes.getString(R.string.three_four_times_week),
                            isSelected = false,
                            type = SignUpSelectorType.RADIO,
                        ),
                        ListSelectorItem(
                            title = stringRes.getString(R.string.five_six_times_week),
                            isSelected = false,
                            type = SignUpSelectorType.RADIO,
                        )
                    ).reversed()
            }
    }
}

data class TrainingUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
){
    fun initial() = copy(
        isLoading = true
    )
}