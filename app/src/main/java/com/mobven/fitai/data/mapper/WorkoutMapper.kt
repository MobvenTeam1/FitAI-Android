package com.mobven.fitai.data.mapper

import com.mobven.fitai.R
import com.mobven.fitai.data.model.entity.WorkoutEntity
import com.mobven.fitai.data.model.response.FitnessAntrenman
import com.mobven.fitai.data.model.response.Program
import com.mobven.fitai.presentation.home.personal_plan.PersonalPlanModel

fun FitnessAntrenman.toWorkoutEntity() {
    WorkoutEntity(
        day = this.day,
        workoutList = this.program
    )
}

fun Program.toPersonalPlanModelList(): List<PersonalPlanModel> {
    return listOf(
        PersonalPlanModel(
            image = R.drawable.add_exercise,
            name = "First Plan",
            detail = this.exercise_1
        ),
        PersonalPlanModel(
            image = R.drawable.biceyle,
            name = "Second Plan",
            detail = this.exercise_2
        ),
        PersonalPlanModel(
            image = R.drawable.fitness,
            name = "Third Plan",
            detail = this.exercise_3
        ),
        PersonalPlanModel(
            image = R.drawable.add_exercise,
            name = "Fourth Plan",
            detail = this.exercise_4
        ),
        PersonalPlanModel(
            image = R.drawable.walking,
            name = "Fifth Plan",
            detail = this.exercise_5
        ),
        PersonalPlanModel(
            image = R.drawable.running,
            name = "Sixth Plan",
            detail = this.exercise_6
        )
    )
}

