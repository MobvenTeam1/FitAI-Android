package com.mobven.fitai.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobven.fitai.data.model.response.Program

@Entity(tableName = "workout_entity")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workout_id")
    val id: Int = 0,

    @ColumnInfo(name = "workout_day")
    val day: String = "",

    @ColumnInfo(name = "workout_list")
    var workoutList: Program = Program("", "", "", "", "", "")

)