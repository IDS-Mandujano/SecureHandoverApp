package com.example.securehandover.features.identity.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.R
import com.example.securehandover.core.ui.theme.*

@Composable
fun SelfiePreviewCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color(0xFFC8B89E)),
        contentAlignment = Alignment.Center
    ) {
        // Reference image provided in drawables
        Image(
            painter = painterResource(id = R.drawable.img_illustrative_selfie),
            contentDescription = "Selfie de ejemplo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Dashed Oval Guide Overlay
        Canvas(
            modifier = Modifier
                .width(180.dp)
                .height(220.dp)
        ) {
            val stroke = Stroke(
                width = 2.5.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 8f), 0f)
            )
            drawOval(
                color = Color.White,
                style = stroke
            )
        }

        // Top Left Badge: Vista de ejemplo · sin grabar
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(14.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceColor)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                text = "Vista de ejemplo · sin grabar",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryText
            )
        }

        // Bottom Instruction Overlay Bar: Centra tu rostro dentro del óvalo
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(14.dp)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(14.dp))
                .background(TrustGreen)
                .padding(vertical = 10.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Centra tu rostro dentro del óvalo",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}
