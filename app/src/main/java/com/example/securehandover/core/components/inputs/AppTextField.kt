package com.example.securehandover.core.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.securehandover.core.ui.theme.ActionGreen
import com.example.securehandover.core.ui.theme.BorderColor
import com.example.securehandover.core.ui.theme.PrimaryText
import com.example.securehandover.core.ui.theme.RiskText
import com.example.securehandover.core.ui.theme.SecondaryText

enum class TextFieldState {
    NEUTRAL,
    TRUST,
    ERROR
}

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIconPainter: Painter? = null,
    trailingIconPainter: Painter? = null,
    state: TextFieldState = TextFieldState.NEUTRAL,
    supportingText: String? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    enabled: Boolean = true
) {
    val borderColor = when (state) {
        TextFieldState.NEUTRAL -> BorderColor
        TextFieldState.TRUST -> ActionGreen
        TextFieldState.ERROR -> RiskText
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = singleLine,
        label = if (label.isNotBlank()) { { Text(label) } } else null,
        placeholder = { Text(placeholder, color = SecondaryText) },

        textStyle = TextStyle(
            color = PrimaryText,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        ),

        leadingIcon = {
            if (leadingIconPainter != null) {
                Icon(
                    painter = leadingIconPainter,
                    contentDescription = null,
                    tint = SecondaryText
                )
            }
        },

        trailingIcon = {
            if (trailingIconPainter != null) {
                Icon(
                    painter = trailingIconPainter,
                    contentDescription = null,
                    tint = SecondaryText
                )
            }
        },

        supportingText = {
            if (supportingText != null) {
                Text(
                    text = supportingText,
                    style = MaterialTheme.typography.bodySmall,
                    color = SecondaryText
                )
            }
        },

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            errorBorderColor = RiskText,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}