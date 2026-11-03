package com.example.securehandover.features.auth.domain.repository

import com.example.securehandover.features.auth.data.model.AuthDto
import com.example.securehandover.features.auth.domain.model.User

interface AuthRepository {
    suspend fun singIn(email: String, password: String) : Result<User>
    suspend fun singUp(username: String, email: String, password: String) : Result<User>
    suspend fun verifyOtp(email: String, code: String): AuthDto
    suspend fun signInWithGoogle() : Result<Unit>
    suspend fun getCurrentUsersession() : Result<User>
    suspend fun logout()

    suspend fun getCurrentUser(): AuthDto?
}