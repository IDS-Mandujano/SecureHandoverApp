package com.example.securehandover.features.identity.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.securehandover.features.identity.presentation.components.SelfiePreviewCard

@Composable
fun IdentityVerificationStep2Screen(
    onBackClick: () -> Unit = {},
    onStartSelfieClick: () -> Unit = {}
) {
    AppScaffold(
        showBackButton = true,
        onBackClick = onBackClick,
        bottomBar = {
            GlobalBottomActionBar(
                infoText = "Permite el acceso a la cámara para realizar la prueba.",
                buttonText = "Iniciar selfie en vivo",
                onButtonClick = onStartSelfieClick
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
            // Reusable Screen Header Indicator (04 / IDENTIDAD · PASO 2 DE 2 + Comprador badge)
            ScreenHeaderIndicator(
                stepTitle = "04 / IDENTIDAD · PASO 2 DE 2",
                roleText = "Comprador"
            )

            // Headline Title & Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Confirma que eres tú.",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "La selfie en vivo ayuda a vincular tu rostro con tu documento, no con una foto guardada.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )
            }

            // Step Indicator Tabs (01 · Documento capturado vs 02 · Selfie en vivo)
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
                        text = "01 · Documento capturado",
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
                            .background(ActionGreen)
                    )
                    Text(
                        text = "02 · Selfie en vivo",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ActionGreen
                    )
                }
            }

            // Selfie Live Preview Card
            SelfiePreviewCard()

            // 3 Guidance Cards Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Guidance 1: Mira al frente
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                    border = BorderStroke(1.dp, BorderColor)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = null,
                            tint = ActionGreen,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Mira al frente",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            color = PrimaryText,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Guidance 2: Sin lentes oscuros
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                    border = BorderStroke(1.dp, BorderColor)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_eye),
                            contentDescription = null,
                            tint = ActionGreen,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Sin lentes oscuros",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            color = PrimaryText,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Guidance 3: Sigue la guía
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                    border = BorderStroke(1.dp, BorderColor)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrows_left_right),
                            contentDescription = null,
                            tint = ActionGreen,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Sigue la guía",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            color = PrimaryText,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Info Card 1: Después: revisión de identidad
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                border = BorderStroke(1.dp, BorderColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user_round),
                        contentDescription = null,
                        tint = ActionGreen,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(top = 2.dp)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Después: revisión de identidad",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryText
                        )

                        Text(
                            text = "Te avisaremos cuando tu documento y tu selfie hayan sido revisados.",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            // Info Card 2: Tus datos, protegidos
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(ExitColor)
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock_keyhole),
                        contentDescription = null,
                        tint = ActionGreen,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(top = 2.dp)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Tus datos, protegidos",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ActionGreen
                        )

                        Text(
                            text = "El rostro se usa para verificar tu identidad. No aparece en el foro ni en tu perfil público.",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = ActionGreen,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
