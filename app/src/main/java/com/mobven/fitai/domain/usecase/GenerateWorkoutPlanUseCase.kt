package com.mobven.fitai.domain.usecase

import com.mobven.fitai.common.ResponseState
import com.mobven.fitai.data.model.response.GeneratePlanResponse
import com.mobven.fitai.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateWorkoutPlanUseCase @Inject constructor(
    private val repository: WorkoutRepository
) {
    operator fun invoke(authToken: String) : Flow<ResponseState<GeneratePlanResponse>> {
        return repository.generateWorkoutPlan(authToken = authToken)
    }
}