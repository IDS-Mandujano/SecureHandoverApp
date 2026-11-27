package com.example.securehandover.features.profile.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.R
import com.example.securehandover.core.components.badges.AppStatusTag
import com.example.securehandover.core.components.headers.ScreenHeaderIndicator
import com.example.securehandover.core.components.navigation.AppNavigationBar
import com.example.securehandover.core.components.navigation.BottomNavItem
import com.example.securehandover.core.components.scaffold.AppScaffold
import com.example.securehandover.core.ui.theme.*

@Composable
fun IdentitySecurityScreen(
    onBackClick: () -> Unit = {},
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
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header Indicator
            ScreenHeaderIndicator(
                stepTitle = "PERFIL / SEGURIDAD",
                showRoleBadge = false
            )

            // Title & Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Identidad y seguridad",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "Tu estado de verificación, en un solo lugar.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )
            }

            // Identity Verified Status Card
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
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shield_check),
                        contentDescription = null,
                        tint = ActionGreen,
                        modifier = Modifier.size(28.dp)
                    )

                    AppStatusTag(
                        text = "Identidad verificada",
                        iconPainter = painterResource(id = R.drawable.ic_badge_check),
                        containerColor = ExitColor,
                        contentColor = ActionGreen,
                        cornerRadius = 8.dp
                    )

                    Text(
                        text = "Diego M.",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )

                    Text(
                        text = "Documento y prueba de vida validados.",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText
                    )
                }
            }

            // Private Documents Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(TrustGreen)
                    .padding(18.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "Tus documentos son privados.",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "Otros usuarios solo ven tu nombre visible y tu insignia de verificación.",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextOnboarding,
                        lineHeight = 16.sp
                    )
                }
            }

            // Note Text
            Text(
                text = "En cada entrega se vuelve a solicitar la validación necesaria para completar el protocolo seguro.",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = SecondaryText,
                lineHeight = 16.sp
            )

            // Return to Profile Button
            OutlinedButton(
                onClick = onBackClick,
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
                    text = "Volver al perfil",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
