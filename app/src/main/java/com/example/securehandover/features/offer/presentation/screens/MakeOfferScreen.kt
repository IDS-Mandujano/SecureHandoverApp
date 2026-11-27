package com.example.securehandover.features.offer.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun MakeOfferScreen(
    onBackClick: () -> Unit = {},
    onReviewOfferClick: () -> Unit = {}
) {
    var offerAmount by remember { mutableStateOf("28,000") }
    var selectedPaymentMethod by remember { mutableStateOf("escrow") } // "escrow" or "cash"

    AppScaffold(
        showBackButton = true,
        onBackClick = onBackClick,
        bottomBar = {
            GlobalBottomActionBar(
                infoText = "Antes de enviar, verás y aceptarás la comisión de custodia.",
                buttonText = "Revisar oferta y retención",
                onButtonClick = onReviewOfferClick
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
            // Screen Header Indicator (05 / OFERTA DE EJEMPLO + Comprador badge)
            ScreenHeaderIndicator(
                stepTitle = "05 / OFERTA DE EJEMPLO",
                roleText = "Comprador"
            )

            // Headline Title
            Text(
                text = "Acuerda el precio. Protege el pago.",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText,
                lineHeight = 30.sp
            )

            // Summary Product Card
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
                                text = "SK-92481   /   $28,000 MXN",
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

            // Identity Verified Badge
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
                        text = "Identidad verificada · estado de ejemplo",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ActionGreen
                    )
                }
            }

            // Offer Amount Card
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
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Tu oferta",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "$$offerAmount",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )

                        Text(
                            text = "MXN",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "Precio publicado: $28,900 MXN · Elena R.",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText
                    )
                }
            }

            // Section: ¿Cómo quieres pagar?
            Text(
                text = "¿Cómo quieres pagar?",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryText
            )

            // Option 1: Pago digital en custodia (Selected)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (selectedPaymentMethod == "escrow") ExitColor else SurfaceColor)
                    .border(
                        BorderStroke(
                            1.5.dp,
                            if (selectedPaymentMethod == "escrow") ActionGreen else BorderColor
                        ),
                        RoundedCornerShape(16.dp)
                    )
                    .clickable { selectedPaymentMethod = "escrow" }
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f)
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
                                text = "Pago digital en custodia",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryText
                            )

                            Text(
                                text = "Se retiene al enviar tu oferta. Solo se libera al completar el protocolo de entrega.",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = SecondaryText,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    // Radio indicator
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(if (selectedPaymentMethod == "escrow") ActionGreen else Color.Transparent)
                            .border(
                                BorderStroke(
                                    2.dp,
                                    if (selectedPaymentMethod == "escrow") ActionGreen else SecondaryText
                                ),
                                CircleShape
                            )
                    )
                }
            }

            // Option 2: Efectivo presencial (Unselected)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (selectedPaymentMethod == "cash") ExitColor else SurfaceColor)
                    .border(
                        BorderStroke(
                            1.dp,
                            if (selectedPaymentMethod == "cash") ActionGreen else BorderColor
                        ),
                        RoundedCornerShape(16.dp)
                    )
                    .clickable { selectedPaymentMethod = "cash" }
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_id_card),
                            contentDescription = null,
                            tint = SecondaryText,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(top = 2.dp)
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = "Efectivo presencial",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryText
                            )

                            Text(
                                text = "Pago en persona, sin custodia. Recibo digital respaldado por ubicación, fotos y voz.",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = SecondaryText,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    // Radio indicator
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(if (selectedPaymentMethod == "cash") ActionGreen else Color.Transparent)
                            .border(
                                BorderStroke(
                                    2.dp,
                                    if (selectedPaymentMethod == "cash") ActionGreen else SecondaryText
                                ),
                                CircleShape
                            )
                    )
                }
            }

            // Payment Method Details Card (Débito ••• 2048)
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
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_id_card),
                            contentDescription = null,
                            tint = PrimaryText,
                            modifier = Modifier.size(22.dp)
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(
                                text = "Débito ••• 2048",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryText
                            )

                            Text(
                                text = "Diego M. · método de ejemplo",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = SecondaryText
                            )
                        }
                    }

                    Text(
                        text = "Cambiar",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ActionGreen,
                        modifier = Modifier.clickable { }
                    )
                }
            }

            // Breakdown Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Importe en custodia",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText
                        )
                        Text(
                            text = "$28,000 MXN",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryText
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Certificación presencial",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText
                        )
                        Text(
                            text = "$0.50 USD",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryText
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Comisión de custodia",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText
                        )
                        Text(
                            text = "Por confirmar",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryText
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 4.dp),
                        thickness = 1.dp,
                        color = BorderColor
                    )

                    Text(
                        text = "La custodia tiene una tarifa porcentual. El importe final se muestra antes de confirmar; no sanamos monedas distintas.",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText,
                        lineHeight = 15.sp
                    )
                }
            }

            // Protection Banner ("Tu dinero no se libera por adelantado")
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
                        painter = painterResource(id = R.drawable.ic_shield_check),
                        contentDescription = null,
                        tint = ActionGreen,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(top = 2.dp)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Tu dinero no se libera por adelantado",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ActionGreen
                        )

                        Text(
                            text = "GPS, biometría del vendedor, fotos y voz del comprador: se requieren los cuatro pasos, en ese orden.",
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
