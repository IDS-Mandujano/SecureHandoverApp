package com.example.securehandover.features.auth.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.securehandover.R
import com.example.securehandover.core.components.buttons.AppButton
import com.example.securehandover.core.components.inputs.AppTextField
import com.example.securehandover.core.components.inputs.TextFieldState
import com.example.securehandover.core.ui.theme.*
import com.example.securehandover.features.auth.presentation.components.PasswordValidationIndicator
import com.example.securehandover.features.auth.presentation.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    onNavigateToVerify: (String) -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToCompleteProfile: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.resetGoogleLoading()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(uiState.requiresVerification) {
        if (uiState.requiresVerification) {
            viewModel.onVerificationNavigated()
            onNavigateToVerify(uiState.email)
        }
    }

    LaunchedEffect(uiState.isSuccess, uiState.requiresProfileCompletion) {
        if (uiState.requiresProfileCompletion) {
            viewModel.onProfileCompletionNavigated()
            onNavigateToCompleteProfile()
        } else if (uiState.isSuccess) {
            onNavigateToHome()
        }
    }

    val usernameState = when {
        uiState.username.isBlank() -> TextFieldState.NEUTRAL
        uiState.username.length >= 3 -> TextFieldState.TRUST
        else -> TextFieldState.ERROR
    }

    val emailState = when {
        uiState.email.isBlank() -> TextFieldState.NEUTRAL
        uiState.email.contains("@") && uiState.email.contains(".") -> TextFieldState.TRUST
        else -> TextFieldState.ERROR
    }

    val password = uiState.password
    val hasUppercase = password.any { it.isUpperCase() }
    val hasSpecialChar = password.any { !it.isLetterOrDigit() }
    val hasNumber = password.any { it.isDigit() }
    val isPasswordValid = hasUppercase && hasSpecialChar && hasNumber && password.length >= 6

    val passwordState = when {
        password.isBlank() -> TextFieldState.NEUTRAL
        isPasswordValid -> TextFieldState.TRUST
        else -> TextFieldState.ERROR
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .imePadding()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "01 / CREAR CUENTA",
                style = MaterialTheme.typography.labelMedium,
                color = SecondaryText
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Crea tu cuenta segura",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Regístrate con tus datos para generar tu código de verificación por correo o continúa con Google.",
                style = MaterialTheme.typography.bodyMedium,
                color = SecondaryText
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Usuario",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    AppTextField(
                        value = uiState.username,
                        onValueChange = { viewModel.onUsernameChanged(it) },
                        label = "",
                        placeholder = "Tu nombre de usuario",
                        leadingIconPainter = painterResource(R.drawable.ic_user_round),
                        supportingText = if (usernameState == TextFieldState.ERROR) "Mínimo 3 caracteres." else "Usa el nombre que quieres mostrar en la app.",
                        state = usernameState
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Correo",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    AppTextField(
                        value = uiState.email,
                        onValueChange = { viewModel.onEmailChanged(it) },
                        label = "",
                        placeholder = "correo@ejemplo.com",
                        leadingIconPainter = painterResource(R.drawable.ic_email),
                        supportingText = if (emailState == TextFieldState.ERROR) "Ingresa un correo válido." else "Usaremos este correo para enviar tu código de verificación.",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        state = emailState
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Contraseña",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    AppTextField(
                        value = uiState.password,
                        onValueChange = { viewModel.onPasswordChanged(it) },
                        label = "",
                        placeholder = "••••••••",
                        leadingIconPainter = painterResource(R.drawable.ic_lock_keyhole),
                        trailingIconPainter = painterResource(
                            if (uiState.isPasswordVisible) R.drawable.ic_eye_off else R.drawable.ic_eye
                        ),
                        onTrailingIconClick = { viewModel.togglePasswordVisibility() },
                        visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        supportingText = "Crea una contraseña segura.",
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        state = passwordState
                    )

                    PasswordValidationIndicator(password = password)

                    if (uiState.errorMessage != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = uiState.errorMessage!!,
                            style = MaterialTheme.typography.bodySmall,
                            color = RiskText
                        )
                    }

                    Spacer(modifier = Modifier.height(22.dp))

                    AppButton(
                        text = "Registrarme",
                        onClick = { viewModel.register() },
                        isLoading = uiState.isLoading,
                        enabled = !uiState.isLoading && !uiState.isGoogleLoading,
                        height = 50.dp,
                        iconPainter = painterResource(id = R.drawable.ic_arrow_rigth),
                        containerColor = ActionGreen,
                        contentColor = SurfaceColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 1.dp,
                        color = BorderColor
                    )
                    Text(
                        text = " o ",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = SecondaryText
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 1.dp,
                        color = BorderColor
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { viewModel.signInWithGoogle() },
                    enabled = !uiState.isLoading && !uiState.isGoogleLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = SurfaceColor),
                    border = BorderStroke(1.dp, BorderColor)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (uiState.isGoogleLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = ActionGreen
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Conectando con Google...",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium,
                                color = PrimaryText
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_google),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Continuar con Google",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium,
                                color = PrimaryText
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "¿Ya tienes cuenta? ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SecondaryText
                    )
                    Text(
                        text = "Inicia sesión aquí",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = ActionGreen,
                        modifier = Modifier.clickable {
                            viewModel.clearError()
                            onNavigateToLogin()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
