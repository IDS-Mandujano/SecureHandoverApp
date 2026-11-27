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
import com.example.securehandover.core.components.badges.AppStatusTag
import com.example.securehandover.core.components.buttons.AppButton
import com.example.securehandover.core.components.headers.ScreenHeaderIndicator
import com.example.securehandover.core.components.navigation.AppNavigationBar
import com.example.securehandover.core.components.navigation.BottomNavItem
import com.example.securehandover.core.components.scaffold.AppScaffold
import com.example.securehandover.core.ui.theme.*

@Composable
fun ListingPreviewScreen(
    title: String = "MacBook Air M2",
    price: String = "$18,500 MXN",
    condition: String = "Muy buen estado",
    locationAndCategory: String = "Roma Norte, CDMX · Tecnología",
    description: String = "MacBook Air M2 en muy buen estado. Incluye cargador. Puedes revisar su funcionamiento durante la entrega.",
    sellerName: String = "Diego M. · vendedor",
    onBackClick: () -> Unit = {},
    onPublishClick: () -> Unit = {},
    onEditClick: () -> Unit = onBackClick,
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

    AppScaffold(
        showBackButton = true,
        onBackClick = onBackClick,
        bottomBar = {
            AppNavigationBar(
                items = navItems,
                currentRoute = "publicar",
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
                stepTitle = "PUBLICAR / VISTA PREVIA",
                showRoleBadge = false
            )

            // Title & Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Así se verá tu anuncio.",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "Borrador de ejemplo - todavía no es público.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )
            }

            // Product Preview Image Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFE2DDD7)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null,
                    tint = SecondaryText.copy(alpha = 0.4f),
                    modifier = Modifier.size(56.dp)
                )
            }

            // Product Title and Price
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                Text(
                    text = price,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            // Condition Tag & Location / Category
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AppStatusTag(
                    text = condition,
                    containerColor = Resume,
                    contentColor = SecondaryText,
                    cornerRadius = 8.dp
                )

                Text(
                    text = locationAndCategory,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText
                )
            }

            // Description Text
            Text(
                text = description,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = SecondaryText,
                lineHeight = 18.sp
            )

            // Seller Info Card
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
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = sellerName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )

                    AppStatusTag(
                        text = "Identidad verificada",
                        iconPainter = painterResource(id = R.drawable.ic_badge_check),
                        containerColor = ExitColor,
                        contentColor = ActionGreen,
                        cornerRadius = 8.dp
                    )
                }
            }

            // Action Buttons
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                AppButton(
                    text = "Publicar anuncio",
                    onClick = onPublishClick,
                    iconPainter = painterResource(id = R.drawable.ic_arrow_rigth),
                    containerColor = ActionGreen,
                    contentColor = SurfaceColor,
                    cornerRadius = 14.dp,
                    height = 50.dp
                )

                OutlinedButton(
                    onClick = onEditClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, BorderColor),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = SurfaceColor,
                        contentColor = PrimaryText
                    )
                ) {
                    Text(
                        text = "Volver a editar",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
