package com.mobven.fitai.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mobven.fitai.data.model.entity.WorkoutEntity
import com.mobven.fitai.data.model.response.Program

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout_entity")
    fun getWorkoutList(): List<WorkoutEntity>

    @Query("SELECT * FROM workout_entity WHERE workout_day= :workoutDay")
    fun getWorkoutByDay(workoutDay: String): WorkoutEntity

    @Query("UPDATE workout_entity SET workout_list= :workoutList WHERE workout_day= :workoutDay")
    fun updateWorkoutList(workoutDay: String, workoutList: List<Program>)

    @Upsert
    fun insertWorkout(workoutEntity: WorkoutEntity)

}