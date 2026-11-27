package com.example.securehandover.features.identity.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.R
import com.example.securehandover.core.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun IneAnimationCard(
    modifier: Modifier = Modifier
) {
    var isFrontSide by remember { mutableStateOf(true) }

    val rotationY by animateFloatAsState(
        targetValue = if (isFrontSide) 0f else 180f,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
        label = "3DCardFlip"
    )

    // Infinite 3D rotation flip every 3.5 seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(3500)
            isFrontSide = !isFrontSide
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(Resume)
            .border(BorderStroke(1.dp, BorderColor), RoundedCornerShape(22.dp))
            .clickable { isFrontSide = !isFrontSide }
            .padding(vertical = 20.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Frame Container with Camera Corner L-Brackets
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentAlignment = Alignment.Center
            ) {
                // Top-Left Corner Bracket
                Canvas(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 12.dp, top = 4.dp)
                        .size(24.dp)
                ) {
                    val strokeWidthPx = 3.dp.toPx()
                    val lengthPx = size.width
                    drawLine(
                        color = ActionGreen,
                        start = Offset(0f, 0f),
                        end = Offset(lengthPx, 0f),
                        strokeWidth = strokeWidthPx,
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = ActionGreen,
                        start = Offset(0f, 0f),
                        end = Offset(0f, lengthPx),
                        strokeWidth = strokeWidthPx,
                        cap = StrokeCap.Round
                    )
                }

                // Top-Right Corner Bracket
                Canvas(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 12.dp, top = 4.dp)
                        .size(24.dp)
                ) {
                    val strokeWidthPx = 3.dp.toPx()
                    val lengthPx = size.width
                    drawLine(
                        color = ActionGreen,
                        start = Offset(0f, 0f),
                        end = Offset(lengthPx, 0f),
                        strokeWidth = strokeWidthPx,
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = ActionGreen,
                        start = Offset(lengthPx, 0f),
                        end = Offset(lengthPx, lengthPx),
                        strokeWidth = strokeWidthPx,
                        cap = StrokeCap.Round
                    )
                }

                // Bottom-Left Corner Bracket
                Canvas(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 12.dp, bottom = 4.dp)
                        .size(24.dp)
                ) {
                    val strokeWidthPx = 3.dp.toPx()
                    val lengthPx = size.width
                    drawLine(
                        color = ActionGreen,
                        start = Offset(0f, lengthPx),
                        end = Offset(lengthPx, lengthPx),
                        strokeWidth = strokeWidthPx,
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = ActionGreen,
                        start = Offset(0f, 0f),
                        end = Offset(0f, lengthPx),
                        strokeWidth = strokeWidthPx,
                        cap = StrokeCap.Round
                    )
                }

                // Bottom-Right Corner Bracket
                Canvas(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 12.dp, bottom = 4.dp)
                        .size(24.dp)
                ) {
                    val strokeWidthPx = 3.dp.toPx()
                    val lengthPx = size.width
                    drawLine(
                        color = ActionGreen,
                        start = Offset(0f, lengthPx),
                        end = Offset(lengthPx, lengthPx),
                        strokeWidth = strokeWidthPx,
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = ActionGreen,
                        start = Offset(lengthPx, 0f),
                        end = Offset(lengthPx, lengthPx),
                        strokeWidth = strokeWidthPx,
                        cap = StrokeCap.Round
                    )
                }

                // 3D Flipping INE Card Container
                Box(
                    modifier = Modifier
                        .width(235.dp)
                        .height(130.dp)
                        .graphicsLayer {
                            this.rotationY = rotationY
                            this.cameraDistance = 14f * density
                        }
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceColor)
                        .border(BorderStroke(1.dp, BorderColor), RoundedCornerShape(12.dp))
                        .padding(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                if (rotationY > 90f) {
                                    this.rotationY = 180f
                                }
                            }
                    ) {
                        if (rotationY <= 90f) {
                            // INE Front (Anverso)
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "INSTITUTO NACIONAL ELECTORAL",
                                        fontSize = 8.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = ActionGreen
                                    )
                                    Text(
                                        text = "INE",
                                        fontSize = 8.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SecondaryText
                                    )
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(38.dp)
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(ExitColor),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_user_round),
                                            contentDescription = null,
                                            tint = ActionGreen,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }

                                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                        Text(
                                            text = "DOCUMENTO DE EJEMPLO",
                                            fontSize = 7.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = SecondaryText
                                        )
                                        Box(
                                            modifier = Modifier
                                                .width(90.dp)
                                                .height(5.dp)
                                                .clip(CircleShape)
                                                .background(BorderColor)
                                        )
                                        Box(
                                            modifier = Modifier
                                                .width(70.dp)
                                                .height(5.dp)
                                                .clip(CircleShape)
                                                .background(BorderColor.copy(alpha = 0.6f))
                                        )
                                    }
                                }

                                Text(
                                    text = "CREDENCIAL PARA VOTAR · ANVERSO",
                                    fontSize = 7.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = SecondaryText
                                )
                            }
                        } else {
                            // INE Back (Reverso)
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(100.dp)
                                            .height(12.dp)
                                            .clip(RoundedCornerShape(2.dp))
                                            .background(SecondaryText.copy(alpha = 0.2f))
                                    )

                                    Text(
                                        text = "INE",
                                        fontSize = 8.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SecondaryText
                                    )
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(ExitColor),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_fingerprint),
                                            contentDescription = null,
                                            tint = ActionGreen,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }

                                    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                                        Box(
                                            modifier = Modifier
                                                .width(100.dp)
                                                .height(5.dp)
                                                .clip(CircleShape)
                                                .background(BorderColor)
                                        )
                                        Box(
                                            modifier = Modifier
                                                .width(80.dp)
                                                .height(5.dp)
                                                .clip(CircleShape)
                                                .background(BorderColor.copy(alpha = 0.6f))
                                        )
                                    }
                                }

                                Text(
                                    text = "FIRMA Y REVERSO DE DOCUMENTO",
                                    fontSize = 7.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = SecondaryText
                                )
                            }
                        }
                    }
                }
            }

            // Instruction Text under frame
            Text(
                text = if (rotationY <= 90f) "Coloca el anverso dentro del marco" else "Coloca el reverso dentro del marco",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = SecondaryText
            )
        }
    }
}
