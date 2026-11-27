package com.example.securehandover.features.handovers.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import com.example.securehandover.core.components.headers.ScreenHeaderIndicator
import com.example.securehandover.core.components.navigation.AppNavigationBar
import com.example.securehandover.core.components.navigation.BottomNavItem
import com.example.securehandover.core.components.scaffold.AppScaffold
import com.example.securehandover.core.ui.theme.*
import com.example.securehandover.features.home.presentation.components.CategoryChip

@Composable
fun HandoversListScreen(
    onBackClick: () -> Unit = {},
    onContinueProtocolClick: () -> Unit = {},
    onNavigateToRoute: (String) -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf("Activos (1)") }
    val tabs = listOf("Activos (1)", "Finalizados (0)")

    val navItems = listOf(
        BottomNavItem(
            route = "explorar",
            label = "Explorar",
            selectedIcon = painterResource(id = R.drawable.ic_compass)
        ),
        BottomNavItem(
            route = "traspasos",
            label = "Traspasos",
            selectedIcon = painterResource(id = R.drawable.ic_handshake)
        ),
        BottomNavItem(
            route = "publicar",
            label = "Publicar",
            selectedIcon = painterResource(id = R.drawable.ic_plus_circle)
        ),
        BottomNavItem(
            route = "perfil",
            label = "Perfil",
            selectedIcon = painterResource(id = R.drawable.ic_user_round)
        )
    )

    AppScaffold(
        showBackButton = true,
        onBackClick = onBackClick,
        bottomBar = {
            AppNavigationBar(
                items = navItems,
                currentRoute = "traspasos",
                onNavigate = onNavigateToRoute
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
            // Header Indicator
            ScreenHeaderIndicator(
                stepTitle = "11 / TRASPASOS",
                roleText = "Comprador"
            )

            // Headline Title & Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Cada trato, bajo control.",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "Consulta tus acuerdos y continúa cada entrega desde donde la dejaste.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )
            }

            // Status Tabs (Activos vs Finalizados)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 2.dp)
            ) {
                items(tabs) { tabText ->
                    CategoryChip(
                        text = tabText,
                        isSelected = tabText == selectedTab,
                        onClick = { selectedTab = tabText }
                    )
                }
            }

            // Main Active Handover Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
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
                    // Status Tag: Validación pendiente
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Resume)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_border_flower),
                                contentDescription = null,
                                tint = SecondaryText,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "Validación pendiente",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SecondaryText
                            )
                        }
                    }

                    // Product Details Row (Image + Title + Price + Code)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFE2DDD7)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = null,
                                tint = SecondaryText,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(
                                text = "Sony α7 IV · cuerpo",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryText
                            )

                            Text(
                                text = "$28,000 MXN",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )

                            Text(
                                text = "SH-92481   ·   Comprando",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = SecondaryText
                            )
                        }
                    }

                    // Seller Verification Line
                    Text(
                        text = "Con Elena R. · Identidad verificada",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = ActionGreen
                    )

                    // Inner Meeting Card
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Resume)
                            .padding(14.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = "Sáb. 3 oct. · 12:30 h",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryText
                            )

                            Text(
                                text = "Café Punto Roma · terraza",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryText
                            )

                            Text(
                                text = "Colima 145, Roma Norte, CDMX",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = SecondaryText
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = "Cita aceptada por ambos",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = ActionGreen
                            )
                        }
                    }

                    // Next Step Instruction
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Siguiente paso: validar ubicación",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryText
                        )

                        Text(
                            text = "En el punto de encuentro, activa tu GPS para iniciar el protocolo presencial.",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText,
                            lineHeight = 16.sp
                        )
                    }

                    // Action Button: Ver cita y continuar
                    Button(
                        onClick = onContinueProtocolClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ActionGreen,
                            contentColor = SurfaceColor
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Ver cita y continuar",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_rigth),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            // Banner Card: Tu entrega, paso a paso
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(TrustGreen)
                    .padding(20.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Tu entrega, paso a paso.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    listOf(
                        "01  Ubicación de ambos en el punto",
                        "02  Autorización biométrica del vendedor",
                        "03  Escaneo y evidencia fotográfica",
                        "04  Firma de voz del comprador"
                    ).forEach { stepText ->
                        Text(
                            text = stepText,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = TextOnboarding
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "El acta digital se genera al completar los cuatro pasos.",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextOnboarding
                    )
                }
            }

            // Section: Tus actas, en un solo lugar
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Tus actas, en un solo lugar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryText
                )

                Text(
                    text = "Aún no tienes traspasos finalizados. Aquí aparecerán tus actas y calificaciones al cerrar una entrega.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Datos ilustrativos del acuerdo SH-92481.",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
