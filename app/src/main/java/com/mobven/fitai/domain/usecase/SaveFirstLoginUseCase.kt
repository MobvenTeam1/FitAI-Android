package com.mobven.fitai.domain.usecase

import com.mobven.fitai.common.ResponseState
import com.mobven.fitai.data.model.dto.FirstLoginDto
import com.mobven.fitai.domain.repository.FitAIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveFirstLoginUseCase @Inject constructor(
    private val repository: FitAIRepository
) {
    operator fun invoke(authToken: String, saveFirstLoginDto: FirstLoginDto) : Flow<ResponseState<String>> {
        return repository.saveFirstLogin(authToken, saveFirstLoginDto)
    }
}