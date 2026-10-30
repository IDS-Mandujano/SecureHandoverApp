package com.example.securehandover.features.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.securehandover.core.ui.theme.RiskText

@Composable
fun PasswordValidationIndicator(password: String) {
    val hasUppercase = password.any { it.isUpperCase() }
    val hasNumber = password.any { it.isDigit() }
    val hasSpecialChar = password.any { !it.isLetterOrDigit() }
    val isPasswordValid = hasUppercase && hasSpecialChar && hasNumber && password.length >= 6

    if (password.isNotBlank() && !isPasswordValid) {
        Spacer(modifier = Modifier.height(6.dp))
        Column(modifier = Modifier.padding(start = 4.dp)) {
            if (!hasUppercase) {
                Text(text = "# Debe incluir al menos una mayúscula (ABCD)", style = MaterialTheme.typography.bodySmall, color = RiskText)
            }
            if (!hasNumber) {
                Text(text = "# Debe incluir al menos un número (1234)", style = MaterialTheme.typography.bodySmall, color = RiskText)
            }
            if (!hasSpecialChar) {
                Text(text = "# Debe incluir al menos un carácter especial ($#%?)", style = MaterialTheme.typography.bodySmall, color = RiskText)
            }
            if (password.length < 6) {
                Text(text = "# Mínimo 6 caracteres", style = MaterialTheme.typography.bodySmall, color = RiskText)
            }
        }
    }
}