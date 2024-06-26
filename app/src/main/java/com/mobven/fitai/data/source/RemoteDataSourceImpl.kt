package com.mobven.fitai.data.source

import com.mobven.fitai.data.model.dto.FirstLoginDto
import com.mobven.fitai.data.model.dto.SignInDto
import com.mobven.fitai.data.model.dto.SignUpDto
import com.mobven.fitai.data.model.dto.WorkoutDetailsDto
import com.mobven.fitai.data.model.response.GeneratePlanResponse
import com.mobven.fitai.data.model.response.LoginResponse
import com.mobven.fitai.data.remote.FitAIService
import com.mobven.fitai.domain.source.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val fitAIService: FitAIService
) : RemoteDataSource {

    override suspend fun registerUser(registerUser: SignUpDto): LoginResponse {
        return fitAIService.registerUser(registerUser)
    }

    override suspend fun loginUser(loginUser: SignInDto): LoginResponse {
        return fitAIService.loginUser(loginUser)
    }

    override suspend fun saveFirstLogin(authToken: String, firstLoginDto: FirstLoginDto): Boolean {
        return fitAIService.saveFirstLogin("Bearer $authToken", firstLoginDto)
    }

    override suspend fun saveWorkoutDetails(
        authToken: String,
        workoutDetailsDto: WorkoutDetailsDto
    ): Boolean {
        return fitAIService.saveWorkoutDetails("Bearer $authToken", workoutDetailsDto)
    }

    override suspend fun generateWorkoutPlan(authToken: String): GeneratePlanResponse {
        return fitAIService.generateWorkoutPlan("Bearer $authToken")
    }

}