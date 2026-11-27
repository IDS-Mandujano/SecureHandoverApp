package com.example.securehandover.features.protocol.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.R
import com.example.securehandover.core.ui.theme.*

@Composable
fun GpsMapCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color(0xFFF2F5F3))
            .border(BorderStroke(1.dp, BorderColor), RoundedCornerShape(22.dp)),
        contentAlignment = Alignment.Center
    ) {
        // Canvas Drawing Mock Map Blocks & Streets
        Canvas(modifier = Modifier.fillMaxSize()) {
            val blockColor = Color(0xFFD3E0D8)
            val cornerRad = CornerRadius(12.dp.toPx(), 12.dp.toPx())

            // Block 1: Top Left
            drawRoundRect(
                color = blockColor,
                topLeft = Offset(16.dp.toPx(), 16.dp.toPx()),
                size = Size(100.dp.toPx(), 60.dp.toPx()),
                cornerRadius = cornerRad
            )

            // Block 2: Top Middle
            drawRoundRect(
                color = blockColor,
                topLeft = Offset(130.dp.toPx(), 20.dp.toPx()),
                size = Size(50.dp.toPx(), 55.dp.toPx()),
                cornerRadius = cornerRad
            )

            // Block 3: Top Middle 2
            drawRoundRect(
                color = blockColor,
                topLeft = Offset(190.dp.toPx(), 20.dp.toPx()),
                size = Size(50.dp.toPx(), 55.dp.toPx()),
                cornerRadius = cornerRad
            )

            // Block 4: Top Right
            drawRoundRect(
                color = blockColor,
                topLeft = Offset(250.dp.toPx(), 16.dp.toPx()),
                size = Size(110.dp.toPx(), 60.dp.toPx()),
                cornerRadius = cornerRad
            )

            // Block 5: Middle Left
            drawRoundRect(
                color = blockColor,
                topLeft = Offset(16.dp.toPx(), 90.dp.toPx()),
                size = Size(100.dp.toPx(), 55.dp.toPx()),
                cornerRadius = cornerRad
            )

            // Block 6: Middle Right
            drawRoundRect(
                color = blockColor,
                topLeft = Offset(250.dp.toPx(), 90.dp.toPx()),
                size = Size(110.dp.toPx(), 55.dp.toPx()),
                cornerRadius = cornerRad
            )

            // Block 7: Bottom Left
            drawRoundRect(
                color = blockColor,
                topLeft = Offset(16.dp.toPx(), 160.dp.toPx()),
                size = Size(100.dp.toPx(), 50.dp.toPx()),
                cornerRadius = cornerRad
            )

            // Block 8: Bottom Middle
            drawRoundRect(
                color = blockColor,
                topLeft = Offset(130.dp.toPx(), 160.dp.toPx()),
                size = Size(110.dp.toPx(), 50.dp.toPx()),
                cornerRadius = cornerRad
            )

            // Block 9: Bottom Right
            drawRoundRect(
                color = blockColor,
                topLeft = Offset(250.dp.toPx(), 160.dp.toPx()),
                size = Size(110.dp.toPx(), 50.dp.toPx()),
                cornerRadius = cornerRad
            )

            // Geofence Circle Overlay
            val centerOffset = Offset(size.width / 2f, size.height / 2f - 4.dp.toPx())
            val circleRadius = 45.dp.toPx()

            drawCircle(
                color = ActionGreen.copy(alpha = 0.15f),
                radius = circleRadius,
                center = centerOffset
            )

            drawCircle(
                color = ActionGreen,
                radius = circleRadius,
                center = centerOffset,
                style = Stroke(
                    width = 2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f), 0f)
                )
            )

            // Buyer Position Dot
            drawCircle(
                color = PrimaryText,
                radius = 6.dp.toPx(),
                center = Offset(centerOffset.x - 18.dp.toPx(), centerOffset.y + 16.dp.toPx())
            )
            drawCircle(
                color = Color.White,
                radius = 2.5.dp.toPx(),
                center = Offset(centerOffset.x - 18.dp.toPx(), centerOffset.y + 16.dp.toPx())
            )
        }

        // Street Labels
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "ROMA NORTE",
                fontSize = 8.sp,
                fontWeight = FontWeight.SemiBold,
                color = SecondaryText,
                modifier = Modifier.padding(start = 28.dp, top = 32.dp)
            )

            Text(
                text = "COLIMA",
                fontSize = 8.sp,
                fontWeight = FontWeight.SemiBold,
                color = SecondaryText,
                modifier = Modifier.padding(start = 48.dp, top = 78.dp)
            )

            Text(
                text = "DURANGO",
                fontSize = 8.sp,
                fontWeight = FontWeight.SemiBold,
                color = SecondaryText,
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 54.dp)
            )
        }

        // Meeting Green Pin Marker in Center
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 12.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(ActionGreen),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_map_pin),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }

        // Bottom Left Tag: Mapa ilustrativo · radio < 15 m
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(SurfaceColor)
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = "Mapa ilustrativo · radio < 15 m",
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = SecondaryText
            )
        }

        // Bottom Right Target Button
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(SurfaceColor)
                .border(BorderStroke(1.dp, BorderColor), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_map_pin),
                contentDescription = "Recentrar mapa",
                tint = ActionGreen,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
