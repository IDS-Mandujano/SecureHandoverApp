package com.example.securehandover.features.profile.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
fun PrivacySettingsScreen(
    onBackClick: () -> Unit = {},
    onSavePreferencesClick: () -> Unit = onBackClick,
    onNavigateToRoute: (String) -> Unit = {}
) {
    var notificationsEnabled by remember { mutableStateOf(true) }

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
                currentRoute = "perfil",
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
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // Header Indicator
            ScreenHeaderIndicator(
                stepTitle = "PERFIL / PREFERENCIAS",
                showRoleBadge = false
            )

            // Title & Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Privacidad y avisos",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "Solo los ajustes esenciales para tus tratos.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )
            }

            // Public Location Card
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
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_map_pin),
                        contentDescription = null,
                        tint = ActionGreen,
                        modifier = Modifier.size(24.dp)
                    )

                    Text(
                        text = "Ubicación pública",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )

                    Text(
                        text = "Solo se muestra tu zona aproximada. Tu dirección exacta no aparece en tu perfil ni en tus anuncios.",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText,
                        lineHeight = 16.sp
                    )
                }
            }

            // Activity Notifications Card
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
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Notificaciones de actividad",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )

                    Text(
                        text = "Recibe avisos sobre ofertas, citas y avances de tus traspasos.",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText,
                        lineHeight = 16.sp
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        AppStatusTag(
                            text = "Activadas",
                            containerColor = if (notificationsEnabled) ExitColor else Resume,
                            contentColor = if (notificationsEnabled) ActionGreen else SecondaryText,
                            cornerRadius = 8.dp,
                            modifier = Modifier.clickable { notificationsEnabled = true }
                        )

                        AppStatusTag(
                            text = "Desactivadas",
                            containerColor = if (!notificationsEnabled) ExitColor else Resume,
                            contentColor = if (!notificationsEnabled) ActionGreen else SecondaryText,
                            cornerRadius = 8.dp,
                            modifier = Modifier.clickable { notificationsEnabled = false }
                        )
                    }
                }
            }

            // Action Button
            AppButton(
                text = "Guardar preferencias",
                onClick = onSavePreferencesClick,
                iconPainter = painterResource(id = R.drawable.ic_arrow_rigth),
                containerColor = ActionGreen,
                contentColor = SurfaceColor,
                cornerRadius = 14.dp,
                height = 50.dp
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
