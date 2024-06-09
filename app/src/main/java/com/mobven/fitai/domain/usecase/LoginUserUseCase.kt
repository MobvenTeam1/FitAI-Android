package com.mobven.fitai.domain.usecase

import com.mobven.fitai.common.ResponseState
import com.mobven.fitai.data.dto.SignInDto
import com.mobven.fitai.domain.repository.FitAIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val fitAIRepository: FitAIRepository
){
    operator fun invoke(signInDto: SignInDto) : Flow<ResponseState<String>> {
        return fitAIRepository.loginUser(signInDto)
    }
}