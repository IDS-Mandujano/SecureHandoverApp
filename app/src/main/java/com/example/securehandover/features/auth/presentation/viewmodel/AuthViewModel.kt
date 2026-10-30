package com.example.securehandover.features.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.securehandover.features.auth.domain.repository.AuthRepository
import com.example.securehandover.features.auth.domain.usecase.LoginUseCase
import com.example.securehandover.features.auth.presentation.state.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val authRepository: AuthRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun onUsernameChanged(value: String){
        _uiState.update { it.copy(username = value) }
    }

    fun onEmailChanged(value: String){
        _uiState.update { it.copy(email = value) }
    }

    fun onPasswordChanged(value: String){
        _uiState.update { it.copy(password = value) }
    }

    fun authenticate(){
        val state = _uiState.value
        if (state.email.isBlank() || state.password.isBlank()){
            _uiState.update { it.copy(errorMessage = "Por favor completa los campos obligatorios") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null)}

            val result = if (state.username.isNotBlank()){
                authRepository.singUp(state.username, state.email, state.password)
            } else {
                loginUseCase(state.email, state.password)
            }

            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                },
                onFailure = { error ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = error.localizedMessage ?: "Ocurrio un error") }
                }
            )
        }
    }

    fun signInWithGoogle(){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = authRepository.singInWithGoogle()

            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                },
                onFailure = { error ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = error.localizedMessage ?: "Error con Google OAuth") }
                }
            )
        }
    }
}