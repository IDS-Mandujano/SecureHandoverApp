package com.example.securehandover.core.components.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.core.ui.theme.Resume
import com.example.securehandover.core.ui.theme.SecondaryText

@Composable
fun ScreenHeaderIndicator(
    stepTitle: String,
    roleText: String? = "Comprador",
    showRoleBadge: Boolean = true,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stepTitle.uppercase(),
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = SecondaryText,
            letterSpacing = 1.sp
        )

        if (showRoleBadge && !roleText.isNullOrBlank()) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Resume)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = roleText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryText
                )
            }
        }
    }
}
