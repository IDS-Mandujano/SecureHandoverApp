package com.example.securehandover.features.protocol.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.securehandover.features.identity.presentation.components.SelfiePreviewCard

@Composable
fun HandoverProtocolStep2Screen(
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {},
    onReportFreezeClick: () -> Unit = {}
) {
    AppScaffold(
        showBackButton = true,
        onBackClick = onBackClick,
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceColor)
            ) {
                GlobalBottomActionBar(
                    infoText = "Paso 2 de 4: Biometría del vendedor para autorizar la liberación.",
                    buttonText = "Continuar a evidencia fotográfica",
                    onButtonClick = onContinueClick
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onReportFreezeClick() }
                        .padding(bottom = 12.dp, top = 2.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_flag),
                        contentDescription = "Denunciar y congelar",
                        tint = RiskText,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Denunciar y congelar · adjuntar evidencia",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = RiskText
                    )
                }
            }
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
                stepTitle = "07 / PROTOCOLO DE EJEMPLO",
                roleText = "Comprador"
            )

            // Protocol 4 Steps Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Step 1: GPS (Completed)
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
                        text = "01 · GPS ✓",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ActionGreen
                    )
                }

                // Step 2: Biometría (Active)
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
                        text = "02 · Biometría",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ActionGreen
                    )
                }

                // Step 3: Fotos (Inactive)
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
                        text = "03 · Fotos",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText
                    )
                }

                // Step 4: Voz (Inactive)
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
                        text = "04 · Voz",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText
                    )
                }
            }

            // Headline Title & Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Autorización del vendedor.",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "El vendedor debe confirmar la entrega escaneando su rostro para autorizar el traspaso.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )
            }

            // Product Summary Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Resume),
                border = BorderStroke(1.dp, BorderColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(SurfaceColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = null,
                                tint = PrimaryText,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(
                                text = "Sony α7 IV · cuerpo",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryText
                            )

                            Text(
                                text = "SH-02481   /   $28,000 MXN",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = SecondaryText
                            )
                        }
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock_keyhole),
                        contentDescription = null,
                        tint = ActionGreen,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Location Verified Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(ExitColor)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shield_check),
                        contentDescription = null,
                        tint = ActionGreen,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "Ubicación confirmada · 8 m de distancia",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ActionGreen
                    )
                }
            }

            // Biometric Face Preview Container
            SelfiePreviewCard()

            // Info Security Card
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
                            text = "Autorización biométrica única",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ActionGreen
                        )

                        Text(
                            text = "Solo la vendedora registrada (Elena R.) puede validar la entrega. Su rostro se cotejará con su INE guardado.",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = ActionGreen,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            // Disclaimer Note
            Text(
                text = "Mantengan la ubicación activa durante todo el protocolo de entrega.",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = SecondaryText,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
