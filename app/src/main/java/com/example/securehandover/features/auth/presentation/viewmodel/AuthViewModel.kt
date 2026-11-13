package com.example.securehandover.features.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.securehandover.features.auth.data.datasource.AuthLocalDataSource
import com.example.securehandover.features.auth.data.model.AuthEntity
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
    private val supabaseClient: SupabaseClient,
    private val authLocalDataSource: AuthLocalDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    init {
        observeSessionStatus()
    }

    private fun observeSessionStatus() {
        viewModelScope.launch {
            supabaseClient.auth.sessionStatus.collect { status ->
                if (status is SessionStatus.Authenticated) {
                    val user = supabaseClient.auth.currentUserOrNull()
                    if (user != null) {
                        val username = user.userMetadata?.get("username")?.toString()?.replace("\"", "")
                            ?: user.userMetadata?.get("full_name")?.toString()?.replace("\"", "")
                            ?: user.userMetadata?.get("name")?.toString()?.replace("\"", "")
                            ?: user.email?.substringBefore("@")
                            ?: ""
                        authLocalDataSource.saveUser(AuthEntity(user.id, username, user.email.orEmpty()))
                    }
                    _uiState.update { it.copy(isSuccess = true, isGoogleLoading = false) }
                }
            }
        }
    }

    fun resetAuthState() {
        _uiState.value = AuthUiState()
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
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

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun login() {
        val state = _uiState.value
        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Por favor completa el correo y la contraseña") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, isSuccess = false, errorMessage = null) }

            loginUseCase(state.email, state.password).fold(
                onSuccess = {
                    val user = supabaseClient.auth.currentUserOrNull()
                    if (user != null) {
                        val username = user.userMetadata?.get("username")?.toString()?.replace("\"", "").orEmpty()
                            .ifBlank { state.email.substringBefore("@") }
                        authLocalDataSource.saveUser(AuthEntity(user.id, username, state.email))
                    }
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            requiresVerification = false,
                            isSuccess = true
                        )
                    }
                },
                onFailure = { error ->
                    val rawMsg = error.localizedMessage.orEmpty()
                    val userFriendlyMsg = when {
                        rawMsg.contains("Invalid login credentials", ignoreCase = true) -> {
                            "Este correo no está registrado o la contraseña es incorrecta. Si te registraste con Google, inicia sesión con Google."
                        }
                        rawMsg.contains("Email not confirmed", ignoreCase = true) -> {
                            "Tu correo aún no ha sido confirmado. Revisa tu bandeja de entrada o verifica tu cuenta."
                        }
                        else -> error.localizedMessage ?: "Ocurrió un error al iniciar sesión"
                    }

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = userFriendlyMsg
                        )
                    }
                }
            )
        }
    }

    fun register() {
        val state = _uiState.value

        if (state.username.isBlank() || state.email.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Por favor completa todos los campos para registrarte") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            authRepository.singUp(state.username, state.email, state.password).fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            requiresVerification = true,
                            isSuccess = false
                        )
                    }
                },
                onFailure = { error ->
                    val rawMsg = error.localizedMessage.orEmpty()
                    val userFriendlyMsg = when {
                        rawMsg.contains("already registered", ignoreCase = true) ||
                        rawMsg.contains("already exists", ignoreCase = true) ||
                        rawMsg.contains("User already registered", ignoreCase = true) -> {
                            "Este correo ya está registrado. Si usaste Gmail, inicia sesión con Google; el código de verificación solo se envía al registrar una cuenta nueva."
                        }
                        else -> error.localizedMessage ?: "Error al registrar la cuenta"
                    }

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = userFriendlyMsg
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
                    val user = supabaseClient.auth.currentUserOrNull()
                    if (user != null) {
                        val username = user.userMetadata?.get("username")?.toString()?.replace("\"", "").orEmpty()
                            .ifBlank { email.substringBefore("@") }
                        authLocalDataSource.saveUser(AuthEntity(user.id, username, email))
                    }
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
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
                onSuccess = {},
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

    suspend fun getDisplayName(): String {
        val localUser = authLocalDataSource.getUser()
        if (!localUser?.username.isNullOrBlank()) {
            return localUser.username
        }

        val user = supabaseClient.auth.currentUserOrNull()
        val metadata = user?.userMetadata
        return metadata?.get("username")?.toString()?.replace("\"", "")
            ?: metadata?.get("full_name")?.toString()?.replace("\"", "")
            ?: metadata?.get("name")?.toString()?.replace("\"", "")
            ?: user?.email?.substringBefore("@")
            ?: "Usuario"
    }

    fun logout(onSignedOut: () -> Unit) {
        viewModelScope.launch {
            try {
                authRepository.logout()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            _uiState.value = AuthUiState()
            onSignedOut()
        }
    }
}
