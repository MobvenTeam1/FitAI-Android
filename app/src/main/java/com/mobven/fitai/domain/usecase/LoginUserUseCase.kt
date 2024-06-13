package com.mobven.fitai.domain.usecase

import com.mobven.fitai.common.ResponseState
import com.mobven.fitai.data.model.dto.SignInDto
import com.mobven.fitai.data.model.response.LoginResponse
import com.mobven.fitai.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(signInDto: SignInDto) : Flow<ResponseState<LoginResponse>> {
        return userRepository.loginUser(signInDto)
    }
}