package com.example.securehandover.features.auth.presentation.state

data class AuthUiState(
    val username : String = "",
    val email : String = "",
    val password : String = "",
    val isLoading : Boolean = false,
    val errorMessage : String? = null,
    val isSuccess: Boolean = false
)
