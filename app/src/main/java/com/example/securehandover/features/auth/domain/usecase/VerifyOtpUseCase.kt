package com.example.securehandover.features.auth.domain.usecase

import com.example.securehandover.features.auth.data.model.AuthDto
import com.example.securehandover.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class VerifyOtpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, code : String) : Result<AuthDto> {
        return runCatching {
            repository.verifyOtp(email,code)
        }
    }
}