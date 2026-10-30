package com.example.securehandover.features.auth.domain.usecase

import com.example.securehandover.features.auth.domain.model.User
import com.example.securehandover.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository : AuthRepository
){
    suspend operator fun invoke (email: String, password: String) : Result<User> {
        return  repository.singIn(email,password)
    }
}