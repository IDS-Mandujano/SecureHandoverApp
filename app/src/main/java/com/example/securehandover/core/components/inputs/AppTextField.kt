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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.securehandover.core.ui.theme.ActionGreen
import com.example.securehandover.core.ui.theme.BorderColor
import com.example.securehandover.core.ui.theme.RiskText

enum class TextFieldState {
    NEUTRAL,
    TRUST,
    ERROR
}

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label : String,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIconPainter : Painter? = null,
    trailingIconPainter : Painter? = null,
    state: TextFieldState = TextFieldState.NEUTRAL,
    supportingText : String? = null,
    singleLine : Boolean = true,
    keyboardOptions : KeyboardOptions = KeyboardOptions.Default,
    keyboardActions : KeyboardActions = KeyboardActions.Default,
    enabled : Boolean = true
) {
    val (borderColor, labelColor, contentColor) = when (state) {
        TextFieldState.NEUTRAL -> {
            Triple(
                BorderColor,
                BorderColor,
                BorderColor
            )
        }
        TextFieldState.TRUST -> {
            Triple (
                ActionGreen,
                ActionGreen,
                ActionGreen
            )
        }
        TextFieldState.ERROR -> {
            Triple(
                RiskText,
                RiskText,
                RiskText
            )
        }
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = singleLine,
        label = { Text(label) },
        placeholder = { Text(placeholder) },

        leadingIcon = {
            if (leadingIconPainter != null) {
                Icon(
                    painter = leadingIconPainter,
                    contentDescription = null,
                    tint = labelColor
                )
            }
        },

        trailingIcon = {
            if (trailingIconPainter != null) {
                Icon(
                    painter = trailingIconPainter,
                    contentDescription = null,
                    tint = contentColor
                )
            }
        },

        supportingText = {
            if (supportingText != null && state != TextFieldState.NEUTRAL) {
                Text(text = supportingText)
            }
        },

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            errorBorderColor = MaterialTheme.colorScheme.error,
            focusedLabelColor = labelColor,
            unfocusedLabelColor = labelColor,
        ),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}