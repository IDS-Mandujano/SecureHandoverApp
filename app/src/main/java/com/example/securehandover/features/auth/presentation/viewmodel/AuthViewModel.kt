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
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
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
                        val user = supabaseClient.auth.currentUserOrNull()
                        val metadata = user?.userMetadata
                        val username = metadata?.get("username")?.toString()?.replace("\"", "").orEmpty()
                        val phone = metadata?.get("phone")?.toString()?.replace("\"", "").orEmpty()

                        val needsProfile = username.isBlank() || phone.isBlank()

                        val initialUsername = username.ifBlank {
                            metadata?.get("full_name")?.toString()?.replace("\"", "")
                                ?: metadata?.get("name")?.toString()?.replace("\"", "")
                                ?: user?.email?.substringBefore("@").orEmpty()
                        }

                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isGoogleLoading = false,
                                isSuccess = !needsProfile,
                                requiresProfileCompletion = needsProfile,
                                username = if (it.username.isBlank()) initialUsername else it.username,
                                email = user?.email ?: it.email,
                                phone = if (it.phone.isBlank()) phone else it.phone,
                                errorMessage = null
                            )
                        }
                    }
                    is SessionStatus.NotAuthenticated -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isGoogleLoading = false,
                                isSuccess = false,
                                requiresProfileCompletion = false
                            )
                        }
                    }
                    else -> {}
                }
            }
        }
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

    fun onPhoneChanged(value: String) {
        _uiState.update { it.copy(phone = value) }
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

    fun onProfileCompletionNavigated() {
        _uiState.update { it.copy(requiresProfileCompletion = false) }
    }

    fun resetGoogleLoading() {
        _uiState.update { it.copy(isGoogleLoading = false) }
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
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = runCatching {
                loginUseCase(state.email, state.password)
            }

            result.fold(
                onSuccess = {
                    val user = supabaseClient.auth.currentUserOrNull()
                    val metadata = user?.userMetadata
                    val username = metadata?.get("username")?.toString()?.replace("\"", "").orEmpty()
                    val phone = metadata?.get("phone")?.toString()?.replace("\"", "").orEmpty()
                    val needsProfile = username.isBlank() || phone.isBlank()

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            requiresVerification = false,
                            requiresProfileCompletion = needsProfile,
                            isSuccess = !needsProfile
                        )
                    }
                },
                onFailure = { error ->
                    val rawMsg = error.localizedMessage.orEmpty()
                    val userFriendlyMsg = when {
                        rawMsg.contains("Invalid login credentials", ignoreCase = true) -> {
                            "Credenciales inválidas. Verifica tu correo y contraseña o regístrate si aún no tienes cuenta."
                        }
                        rawMsg.contains("Email not confirmed", ignoreCase = true) -> {
                            "Tu correo aún no ha sido confirmado. Revisa tu bandeja de entrada o completa la verificación."
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

            val result = runCatching {
                authRepository.singUp(state.username, state.email, state.password)
            }

            result.fold(
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
                            "Este correo ya está registrado. Inicia sesión con tu contraseña o con Google."
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
                    val metadata = user?.userMetadata
                    val username = metadata?.get("username")?.toString()?.replace("\"", "").orEmpty()
                    val phone = metadata?.get("phone")?.toString()?.replace("\"", "").orEmpty()
                    val needsProfile = username.isBlank() || phone.isBlank()

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            requiresProfileCompletion = needsProfile,
                            isSuccess = !needsProfile
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

    fun completeProfile(onSuccess: () -> Unit) {
        val state = _uiState.value
        if (state.username.isBlank() || state.phone.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Por favor ingresa tu nombre de usuario y teléfono") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                supabaseClient.auth.updateUser {
                    data = buildJsonObject {
                        put("username", state.username)
                        put("phone", state.phone)
                        put("profile_completed", true)
                    }
                }
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        requiresProfileCompletion = false,
                        isSuccess = true
                    )
                }
                onSuccess()
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.localizedMessage ?: "No se pudieron guardar tus datos"
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
}
