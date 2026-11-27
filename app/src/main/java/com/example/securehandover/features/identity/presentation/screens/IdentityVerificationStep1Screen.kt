package com.example.securehandover.features.identity.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.securehandover.features.identity.presentation.components.IneAnimationCard

@Composable
fun IdentityVerificationStep1Screen(
    onBackClick: () -> Unit = {},
    onScanIneClick: () -> Unit = {}
) {
    AppScaffold(
        showBackButton = true,
        onBackClick = onBackClick,
        bottomBar = {
            GlobalBottomActionBar(
                infoText = "Tus documentos no son visibles en tu perfil público.",
                buttonText = "Escanear INE",
                onButtonClick = onScanIneClick
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
            // Reusable Screen Header Indicator (03 / IDENTIDAD · PASO 1 DE 2 + Comprador badge)
            ScreenHeaderIndicator(
                stepTitle = "03 / IDENTIDAD · PASO 1 DE 2",
                roleText = "Comprador"
            )

            // Headline Title & Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Una identidad. Más confianza.",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "Para comprar o vender, necesitamos un documento oficial y una selfie en vivo.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )
            }

            // Step Indicator Tabs (01 · Documento oficial vs 02 · Selfie en vivo)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Step 1 Column
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(ActionGreen)
                    )
                    Text(
                        text = "01 · Documento oficial",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ActionGreen
                    )
                }

                // Step 2 Column
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(BorderColor)
                    )
                    Text(
                        text = "02 · Selfie en vivo",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText
                    )
                }
            }

            // INE 3D Flip Reference Card
            IneAnimationCard()

            // Card "Antes de empezar" (Suggestions checklist)
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
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "Antes de empezar",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )

                    // Suggestion 1: Light
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_light),
                            contentDescription = null,
                            tint = SecondaryText,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Busca buena luz, sin reflejos.",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText
                        )
                    }

                    // Suggestion 2: Corners
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = null,
                            tint = SecondaryText,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Muestra las cuatro esquinas del documento.",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText
                        )
                    }

                    // Suggestion 3: Original
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_id_card),
                            contentDescription = null,
                            tint = SecondaryText,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Usa el original vigente, no una fotocopia.",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText
                        )
                    }
                }
            }

            // Card "Verificación obligatoria y unívoca"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(ExitColor)
                    .padding(18.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shield_check),
                        contentDescription = null,
                        tint = ActionGreen,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(top = 2.dp)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Verificación obligatoria y unívoca",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ActionGreen
                        )

                        Text(
                            text = "Un documento, una identidad. Los documentos de usuarios sancionados quedan bloqueados permanentemente.",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = ActionGreen,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            // Privacy Disclaimer
            Text(
                text = "Al continuar, autorizas la revisión de tu identidad conforme al Aviso de privacidad. No se publica tu documento.",
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                color = SecondaryText,
                textAlign = TextAlign.Center,
                lineHeight = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
