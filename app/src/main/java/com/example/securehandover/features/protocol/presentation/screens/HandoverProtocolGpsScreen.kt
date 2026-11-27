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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.R
import com.example.securehandover.core.components.bars.GlobalBottomActionBar
import com.example.securehandover.core.components.headers.ScreenHeaderIndicator
import com.example.securehandover.core.components.scaffold.AppScaffold
import com.example.securehandover.core.ui.theme.*
import com.example.securehandover.features.protocol.presentation.components.GpsMapCard

@Composable
fun HandoverProtocolGpsScreen(
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
                    infoText = "Estado ilustrativo: ubicación en rango. No es una medición real.",
                    buttonText = "Continuar a autorización",
                    onButtonClick = onContinueClick
                )

                // Report / Freeze Link below button
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
            // Reusable Screen Header Indicator (06 / PROTOCOLO DE EJEMPLO + Comprador badge)
            ScreenHeaderIndicator(
                stepTitle = "06 / PROTOCOLO DE EJEMPLO",
                roleText = "Comprador"
            )

            // Protocol 4 Steps Row (GPS, Biometría, Fotos, Voz)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Step 1: GPS (Active)
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_map_pin),
                            contentDescription = null,
                            tint = ActionGreen,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "GPS",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ActionGreen
                        )
                    }
                }

                // Step 2: Biometría (Inactive)
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_fingerprint),
                            contentDescription = null,
                            tint = SecondaryText,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "Biometría",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText
                        )
                    }
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = null,
                            tint = SecondaryText,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "Fotos",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText
                        )
                    }
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mic),
                            contentDescription = null,
                            tint = SecondaryText,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "Voz",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText
                        )
                    }
                }
            }

            // Headline Title & Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "El lugar también importa.",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "Primero, comprueba que ambas personas están en el punto de encuentro acordado.",
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

            // GPS Map Card Component
            GpsMapCard()

            // Meeting Location & Time Card
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
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Date & Time Row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_calendar),
                            contentDescription = null,
                            tint = ActionGreen,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Sáb. 3 oct. · 12:30 h",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryText
                        )
                    }

                    // Meeting Location
                    Text(
                        text = "Café Punto Roma · terraza",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )

                    // Address / Notes
                    Text(
                        text = "Colima 145, Roma Norte, CDMX. Punto público propuesto por Elena y acordado por ambos.",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText,
                        lineHeight = 16.sp
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 4.dp),
                        thickness = 1.dp,
                        color = BorderColor
                    )

                    // Meeting Status Footer
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Cita",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText
                        )

                        Text(
                            text = "Aceptada por ambos",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ActionGreen
                        )
                    }
                }
            }

            // GPS Range Status Card ("Ubicación en rango")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(TrustGreen)
                    .padding(18.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(
                                text = "Ubicación en rango",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )

                            Text(
                                text = "Radio requerido: menor a 15 m",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Normal,
                                color = TextOnboarding
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "8",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                            Text(
                                text = "m",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                        }
                    }

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = ActionGreen.copy(alpha = 0.5f)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Diego · comprador",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = TextOnboarding
                        )
                        Text(
                            text = "8 m · en rango",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Elena · vendedora",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = TextOnboarding
                        )
                        Text(
                            text = "6 m · en rango",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                }
            }

            // Disclaimer Note
            Text(
                text = "Mantengan la ubicación activa y no se separen del punto durante la entrega.",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = SecondaryText,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
