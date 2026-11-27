package com.example.securehandover.features.listing.presentation.screens

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.R
import com.example.securehandover.core.components.bars.GlobalBottomActionBar
import com.example.securehandover.core.components.headers.ScreenHeaderIndicator
import com.example.securehandover.core.components.navigation.AppNavigationBar
import com.example.securehandover.core.components.navigation.BottomNavItem
import com.example.securehandover.core.components.scaffold.AppScaffold
import com.example.securehandover.core.ui.theme.*
import com.example.securehandover.features.home.presentation.components.ProductCard
import com.example.securehandover.features.home.presentation.components.ProductItem

@Composable
fun ListingPreviewScreen(
    onBackClick: () -> Unit = {},
    onPublishClick: () -> Unit = {},
    onNavigateToRoute: (String) -> Unit = {}
) {
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

    val previewProduct = ProductItem(
        id = "preview_1",
        title = "Sony α7 IV · cuerpo",
        price = "$28,000 MXN",
        condition = "Excelente estado",
        locationDistance = "Roma Norte, CDMX",
        isVerified = true,
        placeholderColor = Color(0xFFE2DDD7)
    )

    AppScaffold(
        showBackButton = true,
        onBackClick = onBackClick,
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                GlobalBottomActionBar(
                    infoText = "Tu anuncio estará visible inmediatamente en Mercado Local.",
                    buttonText = "Publicar anuncio",
                    onButtonClick = onPublishClick
                )

                AppNavigationBar(
                    items = navItems,
                    currentRoute = "publicar",
                    onNavigate = onNavigateToRoute
                )
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
            // Header Indicator
            ScreenHeaderIndicator(
                stepTitle = "01 / PUBLICAR ANUNCIO",
                roleText = "Vendedor"
            )

            // Title & Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Vista previa de tu anuncio.",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "Revisa los detalles de tu publicación antes de hacerla pública en el mercado local.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )
            }

            // Preview Product Card
            ProductCard(
                product = previewProduct,
                modifier = Modifier.fillMaxWidth()
            )

            // Security & Settings Checklist Card
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
                        text = "Garantías y seguridad activadas",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )

                    listOf(
                        "Certificación presencial de la entrega",
                        "Custodia digital de pago habilitada",
                        "Verificación de ubicación GPS activada",
                        "Firma de voz e identidad biométrica requeridas"
                    ).forEach { featureText ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = featureText,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = SecondaryText
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_badge_check),
                                contentDescription = "Activado",
                                tint = ActionGreen,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            // Information Box
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
                            text = "Protección de vendedora",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ActionGreen
                        )

                        Text(
                            text = "Las ofertas de compradores no verificados serán bloqueadas automáticamente hasta que confirmen su identidad.",
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
