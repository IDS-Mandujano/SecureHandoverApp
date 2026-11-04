package com.example.securehandover.features.auth.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.securehandover.R
import com.example.securehandover.core.components.buttons.AppButton
import com.example.securehandover.core.components.inputs.AppTextField
import com.example.securehandover.core.components.inputs.TextFieldState
import com.example.securehandover.core.ui.theme.*
import com.example.securehandover.features.auth.presentation.viewmodel.AuthViewModel

@Composable
fun CompleteProfileScreen(
    viewModel: AuthViewModel,
    onProfileCompleted: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    val usernameState = when {
        uiState.username.isBlank() -> TextFieldState.NEUTRAL
        uiState.username.length >= 3 -> TextFieldState.TRUST
        else -> TextFieldState.ERROR
    }

    val phoneState = when {
        uiState.phone.isBlank() -> TextFieldState.NEUTRAL
        uiState.phone.length >= 8 -> TextFieldState.TRUST
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
                text = "02 / PERFIL DE SEGURIDAD",
                style = MaterialTheme.typography.labelMedium,
                color = SecondaryText
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Completa tus datos",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Es tu primer inicio de sesión. Revisa o completa tu nombre de usuario y tu teléfono para proteger tus transacciones.",
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
                        text = "Correo",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    AppTextField(
                        value = uiState.email,
                        onValueChange = {},
                        label = "",
                        placeholder = "",
                        leadingIconPainter = painterResource(R.drawable.ic_email),
                        supportingText = "Correo verificado de tu cuenta.",
                        enabled = false
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Nombre de usuario",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    AppTextField(
                        value = uiState.username,
                        onValueChange = { viewModel.onUsernameChanged(it) },
                        label = "",
                        placeholder = "Ej. usuario_seguro",
                        leadingIconPainter = painterResource(R.drawable.ic_user_round),
                        supportingText = if (usernameState == TextFieldState.ERROR) "Mínimo 3 caracteres." else "Nombre con el que te identificarán en los tratos.",
                        state = usernameState
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Número de teléfono",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    AppTextField(
                        value = uiState.phone,
                        onValueChange = { viewModel.onPhoneChanged(it) },
                        label = "",
                        placeholder = "+52 55 1234 5678",
                        leadingIconPainter = painterResource(R.drawable.ic_compass),
                        supportingText = if (phoneState == TextFieldState.ERROR) "Ingresa un número válido." else "Necesario para contactarte en entregas seguras.",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        state = phoneState
                    )

                    if (uiState.errorMessage != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = uiState.errorMessage!!,
                            style = MaterialTheme.typography.bodySmall,
                            color = RiskText
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    AppButton(
                        text = "Guardar y Continuar",
                        onClick = { viewModel.completeProfile(onProfileCompleted) },
                        isLoading = uiState.isLoading,
                        height = 50.dp,
                        iconPainter = painterResource(id = R.drawable.ic_arrow_rigth),
                        containerColor = ActionGreen,
                        contentColor = SurfaceColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
