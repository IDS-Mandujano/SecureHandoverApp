package com.example.securehandover.features.auth.data.repository

import com.example.securehandover.features.auth.data.datasource.AuthLocalDataSource
import com.example.securehandover.features.auth.data.datasource.AuthRemoteDataSource
import com.example.securehandover.features.auth.data.mapper.toDomain
import com.example.securehandover.features.auth.data.mapper.toEntity
import com.example.securehandover.features.auth.domain.model.User
import com.example.securehandover.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    private val localDataSource : AuthLocalDataSource
) : AuthRepository {

    override suspend fun singIn(email: String, password: String): Result<User> {
        return try {
            val dto = remoteDataSource.signIn(email,password)
            val user = dto.toDomain()
            localDataSource.saveUser(user.toEntity())
            Result.success(user)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun singUp(username: String, email: String, password: String): Result<User> {
        return try {
            val dto = remoteDataSource.signUp(username,email,password)
            val user = dto.toDomain()
            localDataSource.saveUser(user.toEntity())
            Result.success(user)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun singInWithGoogle(): Result<User> {
        return try {
            val dto = remoteDataSource.signInWhitGoogle()
            val user = dto.toDomain()
            localDataSource.saveUser(user.toEntity())
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        remoteDataSource.logout()
        localDataSource.clearSession()
    }
}