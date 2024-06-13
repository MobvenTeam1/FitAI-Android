package com.mobven.fitai.domain.repository

import com.mobven.fitai.common.ResponseState
import com.mobven.fitai.data.model.dto.FirstLoginDto
import com.mobven.fitai.data.model.dto.SignInDto
import com.mobven.fitai.data.model.dto.SignUpDto
import com.mobven.fitai.data.model.dto.WorkoutDetailsDto
import com.mobven.fitai.data.model.entity.UserEntity
import com.mobven.fitai.data.model.response.LoginResponse
import kotlinx.coroutines.flow.Flow

interface FitAIRepository {

    // Remote
    fun registerUser(registerUser: SignUpDto) : Flow<ResponseState<LoginResponse>>

    fun loginUser(loginUser: SignInDto) : Flow<ResponseState<LoginResponse>>

    fun saveFirstLogin(authToken: String, firstLoginDto: FirstLoginDto) : Flow<ResponseState<Boolean>>

    fun saveWorkoutDetails(authToken: String, workoutDetailsDto: WorkoutDetailsDto) : Flow<ResponseState<Boolean>>

    // Local
    fun getUser(): Flow<ResponseState<UserEntity>>

    fun getUserKey(): Flow<ResponseState<String>>

    fun insertUser(userEntity: UserEntity)

    fun insertUserKey(userKey: String)

    fun updateUserKey(userKey: String)

}