package com.example.securehandover.features.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.securehandover.features.auth.domain.repository.AuthRepository
import com.example.securehandover.features.auth.domain.usecase.LoginUseCase
import com.example.securehandover.features.auth.domain.usecase.VerifyOtpUseCase
import com.example.securehandover.features.auth.presentation.state.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val verifyOtpUseCase: VerifyOtpUseCase,
    private val authRepository: AuthRepository,
    private val supabaseClient: SupabaseClient
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    init {
        observeSessionStatus()
    }

    private fun observeSessionStatus() {
        viewModelScope.launch {
            supabaseClient.auth.sessionStatus.collect { status ->
                when (status) {
                    is SessionStatus.Authenticated -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isGoogleLoading = false,
                                isSuccess = true,
                                errorMessage = null
                            )
                        }
                    }
                    is SessionStatus.NotAuthenticated -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isGoogleLoading = false,
                                isSuccess = false
                            )
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    fun onUsernameChanged(value: String) {
        _uiState.update { it.copy(username = value) }
    }

    fun onEmailChanged(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun onPasswordChanged(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun resetVerificationState() {
        _uiState.update {
            it.copy(
                requiresVerification = false,
                errorMessage = null,
                isLoading = false
            )
        }
    }

    fun onVerificationNavigated() {
        _uiState.update { it.copy(requiresVerification = false) }
    }

    fun resetGoogleLoading() {
        _uiState.update { it.copy(isGoogleLoading = false) }
    }

    fun authenticate() {
        val state = _uiState.value
        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Por favor completa los campos obligatorios") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = runCatching {
                if (state.username.isNotBlank()) {
                    authRepository.singUp(state.username, state.email, state.password)
                } else {
                    loginUseCase(state.email, state.password)
                }
            }

            result.fold(
                onSuccess = {
                    if (state.username.isNotBlank()) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                requiresVerification = true,
                                isSuccess = false
                            )
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                requiresVerification = false,
                                isSuccess = true
                            )
                        }
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.localizedMessage ?: "Ocurrió un error"
                        )
                    }
                }
            )
        }
    }

    fun onVerifyClicked(email: String, digits: List<String>) {
        val otpCode = digits.joinToString("")

        if (otpCode.length < 6) {
            _uiState.update { it.copy(errorMessage = "Ingresa el código completo de 6 dígitos") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            verifyOtpUseCase(email, otpCode)
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.localizedMessage ?: "Código de verificación inválido"
                        )
                    }
                }
        }
    }

    fun signInWithGoogle() {
        viewModelScope.launch {
            _uiState.update { it.copy(isGoogleLoading = true, errorMessage = null) }

            val result = authRepository.signInWithGoogle()

            result.fold(
                onSuccess = {
                    // Custom Tab abierto, se esperará a que el deeplink entregue la sesión autenticada
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isGoogleLoading = false,
                            errorMessage = error.localizedMessage ?: "Error al abrir Google OAuth"
                        )
                    }
                }
            )
        }
    }
}
