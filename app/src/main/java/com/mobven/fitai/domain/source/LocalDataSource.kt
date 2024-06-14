package com.mobven.fitai.domain.source

import com.mobven.fitai.data.model.entity.UserEntity
import com.mobven.fitai.data.model.entity.WorkoutEntity
import com.mobven.fitai.data.model.response.Program

interface LocalDataSource {

    // User
    fun insertUser(userEntity: UserEntity)

    fun updateUserKey(userKey: String)

    fun insertUserKey(userKey: String)

    suspend fun getUserKey(): String

    suspend fun getUser(): UserEntity

    // Workout
    fun getWorkoutList() : List<WorkoutEntity>

    fun getWorkoutByDay(workoutDay: String) : WorkoutEntity

    fun updateWorkoutList(workoutDay: String, workoutList: List<Program>)

    fun insertWorkout(workoutEntity: WorkoutEntity)

}