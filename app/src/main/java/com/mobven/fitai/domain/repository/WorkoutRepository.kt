package com.mobven.fitai.domain.repository

import com.mobven.fitai.common.ResponseState
import com.mobven.fitai.data.model.entity.WorkoutEntity
import com.mobven.fitai.data.model.response.GeneratePlanResponse
import com.mobven.fitai.data.model.response.Program
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    // Remote
    fun generateWorkoutPlan(authToken: String): Flow<ResponseState<GeneratePlanResponse>>

    // Local
    fun getWorkoutList() : Flow<ResponseState<List<WorkoutEntity>>>

    fun getWorkoutByDay(workoutDay: String) : Flow<ResponseState<WorkoutEntity>>

    fun updateWorkoutList(workoutDay: String, workoutList: List<Program>)

    fun insertWorkout(workoutEntity: WorkoutEntity)

}