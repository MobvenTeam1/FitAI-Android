package com.mobven.fitai.data.remote

import com.mobven.fitai.data.model.dto.FirstLoginDto
import com.mobven.fitai.data.model.dto.SignInDto
import com.mobven.fitai.data.model.dto.SignUpDto
import com.mobven.fitai.data.model.dto.WorkoutDetailsDto
import com.mobven.fitai.data.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface FitAIService {

    @POST("api/User/Register")
    suspend fun registerUser(@Body registerModel: SignUpDto): LoginResponse

    @POST("api/User/Login")
    suspend fun loginUser(
        @Body loginModel: SignInDto
    ): LoginResponse

    @POST("api/User/SaveFirstLoginDetails")
    suspend fun saveFirstLogin(
        @Header("Authorization") token: String,
        @Body saveFirstLoginModel: FirstLoginDto
    ): String

    @POST("api/User/Workout-Details")
    suspend fun saveWorkoutDetails(
        @Header("Authorization") token: String,
        @Body workoutDetailsModel: WorkoutDetailsDto
    ): String

}