package com.example.securehandover.features.auth.domain.repository

import com.example.securehandover.features.auth.domain.model.User

interface AuthRepository {
    suspend fun singIn(email: String, password: String) : Result<User>
    suspend fun singUp(username: String, email: String, password: String) : Result<User>
    suspend fun singInWithGoogle() : Result<User>
    suspend fun logout()
}