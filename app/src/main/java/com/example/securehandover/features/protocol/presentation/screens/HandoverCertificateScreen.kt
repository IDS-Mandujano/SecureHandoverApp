package com.example.securehandover.features.protocol.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.R
import com.example.securehandover.core.components.bars.GlobalBottomActionBar
import com.example.securehandover.core.components.headers.ScreenHeaderIndicator
import com.example.securehandover.core.components.scaffold.AppScaffold
import com.example.securehandover.core.ui.theme.*

@Composable
fun HandoverCertificateScreen(
    onBackClick: () -> Unit = {},
    onReturnHomeClick: () -> Unit = {}
) {
    var rating by remember { mutableStateOf(5) }
    var ratingComment by remember { mutableStateOf("") }

    AppScaffold(
        showBackButton = false,
        onBackClick = onBackClick,
        bottomBar = {
            GlobalBottomActionBar(
                infoText = "Gracias por formar parte de un trato seguro.",
                buttonText = "Volver al inicio",
                onButtonClick = onReturnHomeClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Screen Header Indicator
            ScreenHeaderIndicator(
                stepTitle = "10 / ACTA DIGITAL Y CALIFICACIÓN",
                roleText = "Comprador"
            )

            // Success Certificate Banner Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(22.dp))
                    .background(ExitColor)
                    .border(BorderStroke(1.5.dp, ActionGreen), RoundedCornerShape(22.dp))
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shield_check),
                        contentDescription = "Éxito",
                        tint = ActionGreen,
                        modifier = Modifier.size(48.dp)
                    )

                    Text(
                        text = "Entrega certificada exitosamente",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "El pago de $28,000 MXN ha sido liberado a Elena R.",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceColor)
                            .border(BorderStroke(1.dp, BorderColor), RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Acta Digital #HD-92841-MX",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ActionGreen
                        )
                    }
                }
            }

            // Protocol Summary Breakdown Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                border = BorderStroke(1.dp, BorderColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Evidencia registrada en el acta",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )

                    listOf(
                        "Identidad de partes verificadas",
                        "Ubicación GPS en rango (8 m)",
                        "Biometría del vendedor confirmada",
                        "Evidencia fotográfica (3 fotos)",
                        "Firma de voz del comprador registrada"
                    ).forEach { proofItem ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = proofItem,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = SecondaryText
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_badge_check),
                                contentDescription = "Verificado",
                                tint = ActionGreen,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            // Mutual Rating Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                border = BorderStroke(1.dp, BorderColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "¿Cómo fue tu experiencia con Elena R.?",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText,
                        textAlign = TextAlign.Center
                    )

                    // 5 Rating Stars
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        (1..5).forEach { starIndex ->
                            Icon(
                                painter = painterResource(id = R.drawable.ic_star),
                                contentDescription = "Estrella $starIndex",
                                tint = if (starIndex <= rating) ActionGreen else BorderColor,
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable { rating = starIndex }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    // Optional comment input
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Resume)
                            .border(BorderStroke(1.dp, BorderColor), RoundedCornerShape(12.dp))
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        BasicTextField(
                            value = ratingComment,
                            onValueChange = { ratingComment = it },
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = ActionGreen
                            ),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            decorationBox = { innerTextField ->
                                if (ratingComment.isEmpty()) {
                                    Text(
                                        text = "Escribe un comentario público opcional...",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                        color = SecondaryText
                                    )
                                }
                                innerTextField()
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
