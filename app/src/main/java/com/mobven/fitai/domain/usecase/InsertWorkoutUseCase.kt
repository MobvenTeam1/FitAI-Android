package com.mobven.fitai.domain.usecase

import com.mobven.fitai.data.model.entity.WorkoutEntity
import com.mobven.fitai.domain.repository.WorkoutRepository
import javax.inject.Inject

class InsertWorkoutUseCase @Inject constructor(
    private val repository: WorkoutRepository
) {
    operator fun invoke(workoutEntity: WorkoutEntity) {
        repository.insertWorkout(workoutEntity)
    }
}