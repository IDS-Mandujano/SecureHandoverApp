package com.example.securehandover.features.product.presentation.screens

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.R
import com.example.securehandover.core.components.bars.GlobalBottomActionBar
import com.example.securehandover.core.components.headers.ScreenHeaderIndicator
import com.example.securehandover.core.components.scaffold.AppScaffold
import com.example.securehandover.core.ui.theme.*
import com.example.securehandover.features.product.presentation.components.PublicQuestionItem
import com.example.securehandover.features.product.presentation.components.QuestionData
import com.example.securehandover.features.product.presentation.components.SellerCard
import kotlinx.coroutines.launch

@Composable
fun ProductDetailScreen(
    onBackClick: () -> Unit = {},
    onMakeOfferClick: () -> Unit = {}
) {
    var questionInput by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    val sampleQuestions = remember {
        listOf(
            QuestionData(
                id = "1",
                userNameAndTime = "Diego M. · Hace 20 min",
                questionText = "¿Podemos probar enfoque y grabación 4K?",
                sellerNameTag = "Elena R. · Vendedora",
                sellerResponseText = "Sí. Llevo un lente y una SD para que la pruebes en el punto de encuentro."
            ),
            QuestionData(
                id = "2",
                userNameAndTime = "Ana L. · Ayer",
                questionText = "¿Tienes la factura original?",
                sellerNameTag = "Elena R. · Vendedora",
                sellerResponseText = "Sí, incluyo la factura de compra. El número de serie coincide con la cámara."
            )
        )
    }

    LaunchedEffect(questionInput) {
        if (questionInput.isNotEmpty()) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }
    }

    AppScaffold(
        showBackButton = true,
        onBackClick = onBackClick,
        bottomBar = {
            GlobalBottomActionBar(
                infoText = "Verifica la identidad antes de comprar o vender.",
                buttonText = "Hacer una oferta",
                onButtonClick = onMakeOfferClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Reusable Screen Header Indicator (02 / DETALLE DEL PRODUCTO + Comprador badge)
            ScreenHeaderIndicator(
                stepTitle = "02 / DETALLE DEL PRODUCTO",
                roleText = "Comprador"
            )

            // Product Name
            Text(
                text = "Sony α7 IV",
                fontSize = 26.sp,
                fontWeight = FontWeight.Normal,
                color = PrimaryText,
                lineHeight = 30.sp
            )

            // Large Product Image Box with Photo Counter Indicator
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFE2DDD7)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null,
                    tint = SecondaryText.copy(alpha = 0.4f),
                    modifier = Modifier.size(64.dp)
                )

                // 1 / 4 Fotos indicator on bottom right corner
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceColor)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "1 / 4 fotos",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = PrimaryText
                    )
                }
            }

            // Price and Currency Indicator Row
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "$28,900",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Normal,
                    color = PrimaryText
                )
                Text(
                    text = "MXN",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    modifier = Modifier.padding(bottom = 3.dp)
                )
            }

            // Badges Row: Condition, Body Only, Distance
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Badge 1: Excelente estado
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Resume)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Excelente estado",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText
                    )
                }

                // Badge 2: Solo cuerpo
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Resume)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Solo cuerpo",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText
                    )
                }

                // Badge 3: Distance with map pin icon
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(ExitColor)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
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
                            text = "A 1.2 km",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = ActionGreen
                        )
                    }
                }
            }

            // Product Description
            Text(
                text = "Sensor full-frame de 33 MP. 8,240 disparos. Incluye batería original, cargador y caja. Prueba de funcionamiento en la entrega.",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = SecondaryText,
                lineHeight = 18.sp
            )

            // Seller Card Component
            SellerCard(
                sellerInitials = "ER",
                sellerName = "Elena R.",
                memberSinceAndLocation = "Miembro desde 2024 · Roma Norte",
                rating = "4.9 / 5",
                certifiedDeliveriesCount = "24 entregas certificadas",
                highlightsText = "Puntual · Producto exacto · Ubicación precisa",
                isCertified = true
            )

            // Public Questions Section Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Preguntas públicas",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = PrimaryText
                )

                Text(
                    text = "${sampleQuestions.size} preguntas",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText
                )
            }

            // List of Public Questions
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                sampleQuestions.forEach { question ->
                    PublicQuestionItem(question = question)
                }
            }

            // Send Question Input Field with Custom BasicTextField (no clipping, color 146B53)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(SurfaceColor)
                    .border(BorderStroke(1.dp, BorderColor), RoundedCornerShape(14.dp))
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicTextField(
                        value = questionInput,
                        onValueChange = { questionInput = it },
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = ActionGreen
                        ),
                        singleLine = true,
                        modifier = Modifier.weight(1f),
                        decorationBox = { innerTextField ->
                            if (questionInput.isEmpty()) {
                                Text(
                                    text = "Pregunta algo que todos puedan ver",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = SecondaryText
                                )
                            }
                            innerTextField()
                        }
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_send),
                        contentDescription = "Enviar pregunta",
                        tint = ActionGreen,
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                if (questionInput.isNotBlank()) {
                                    questionInput = ""
                                    coroutineScope.launch {
                                        scrollState.animateScrollTo(scrollState.maxValue)
                                    }
                                }
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
