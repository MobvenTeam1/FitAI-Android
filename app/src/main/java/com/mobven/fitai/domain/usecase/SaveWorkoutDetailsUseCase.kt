package com.mobven.fitai.domain.usecase

import com.mobven.fitai.common.ResponseState
import com.mobven.fitai.data.model.dto.WorkoutDetailsDto
import com.mobven.fitai.domain.repository.FitAIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveWorkoutDetailsUseCase @Inject constructor(
    private val repository: FitAIRepository
) {
    operator fun invoke(
        authToken: String,
        saveWorkoutDetailsDto: WorkoutDetailsDto
    ): Flow<ResponseState<Boolean>> {
        return repository.saveWorkoutDetails(authToken, saveWorkoutDetailsDto)
    }
}