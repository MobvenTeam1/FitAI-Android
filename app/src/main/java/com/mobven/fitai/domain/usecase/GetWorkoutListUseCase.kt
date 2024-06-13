package com.mobven.fitai.domain.usecase

import com.mobven.fitai.common.ResponseState
import com.mobven.fitai.data.model.entity.WorkoutEntity
import com.mobven.fitai.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWorkoutListUseCase @Inject constructor(
    private val repository: WorkoutRepository
) {
    operator fun invoke(): Flow<ResponseState<List<WorkoutEntity>>> {
        return repository.getWorkoutList()
    }
}