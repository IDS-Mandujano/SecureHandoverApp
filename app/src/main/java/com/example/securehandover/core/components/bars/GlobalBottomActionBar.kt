package com.example.securehandover.core.components.bars

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.R
import com.example.securehandover.core.ui.theme.ActionGreen
import com.example.securehandover.core.ui.theme.BorderColor
import com.example.securehandover.core.ui.theme.SecondaryText
import com.example.securehandover.core.ui.theme.SurfaceColor

@Composable
fun GlobalBottomActionBar(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    infoText: String = "Verifica la identidad antes de comprar o vender.",
    buttonText: String = "Hacer una oferta",
    buttonContainerColor: Color = ActionGreen,
    buttonContentColor: Color = Color.White
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = SurfaceColor,
        tonalElevation = 8.dp,
        border = BorderStroke(1.dp, BorderColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = infoText,
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                color = SecondaryText
            )

            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonContainerColor,
                    contentColor = buttonContentColor
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = buttonText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_rigth),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = buttonContentColor
                    )
                }
            }
        }
    }
}
