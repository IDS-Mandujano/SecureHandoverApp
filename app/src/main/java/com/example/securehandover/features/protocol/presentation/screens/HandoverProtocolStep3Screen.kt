package com.example.securehandover.features.protocol.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

@Composable
fun HandoverProtocolStep3Screen(
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
                    infoText = "Paso 3 de 4: Evidencia fotográfica del producto en mano.",
                    buttonText = "Continuar a firma de voz",
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
                stepTitle = "08 / PROTOCOLO DE EJEMPLO",
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

                // Step 2: Biometría (Completed)
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
                        text = "02 · Biometría ✓",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ActionGreen
                    )
                }

                // Step 3: Fotos (Active)
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
                        text = "03 · Fotos",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ActionGreen
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
                    text = "Escaneo y evidencia fotográfica.",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "Toma fotos del producto y del paquete tal como los recibes en el punto de encuentro.",
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

            // Photo Capture Slots Container
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                // Photo Slot 1: Captured
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                    border = BorderStroke(1.dp, ActionGreen)
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
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(ExitColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_camera),
                                    contentDescription = null,
                                    tint = ActionGreen,
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = "1. Estado del producto",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = PrimaryText
                                )
                                Text(
                                    text = "Capturada exitosamente",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = ActionGreen
                                )
                            }
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.ic_badge_check),
                            contentDescription = "Lista",
                            tint = ActionGreen,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                // Photo Slot 2: Captured
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                    border = BorderStroke(1.dp, ActionGreen)
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
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(ExitColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_id_card),
                                    contentDescription = null,
                                    tint = ActionGreen,
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = "2. Número de serie",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = PrimaryText
                                )
                                Text(
                                    text = "Capturada exitosamente",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = ActionGreen
                                )
                            }
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.ic_badge_check),
                            contentDescription = "Lista",
                            tint = ActionGreen,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                // Photo Slot 3: Pending
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Resume),
                    border = BorderStroke(1.dp, BorderColor)
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
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(SurfaceColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_camera),
                                    contentDescription = null,
                                    tint = SecondaryText,
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = "3. Empaque y accesorios",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = PrimaryText
                                )
                                Text(
                                    text = "Toca para tomar foto opcional",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = SecondaryText
                                )
                            }
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.ic_plus_circle),
                            contentDescription = "Agregar foto",
                            tint = PrimaryText,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }

            // Info Warning Card
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
                            text = "Evidencia inmutable",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ActionGreen
                        )

                        Text(
                            text = "Registren cualquier detalle estético previo antes de firmar. Las fotos se cifran y adjuntan al acta digital.",
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
