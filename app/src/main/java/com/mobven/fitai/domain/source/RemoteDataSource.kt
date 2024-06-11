package com.mobven.fitai.domain.source

import com.mobven.fitai.data.model.dto.FirstLoginDto
import com.mobven.fitai.data.model.response.LoginResponse
import com.mobven.fitai.data.model.dto.SignInDto
import com.mobven.fitai.data.model.dto.SignUpDto
import com.mobven.fitai.data.model.dto.WorkoutDetailsDto

interface RemoteDataSource {

    suspend fun registerUser(registerUser: SignUpDto): LoginResponse

    suspend fun loginUser(loginUser: SignInDto): LoginResponse

    suspend fun saveFirstLogin(authToken: String, firstLoginDto: FirstLoginDto): String

    suspend fun saveWorkoutDetails(authToken: String, workoutDetailsDto: WorkoutDetailsDto): String

}