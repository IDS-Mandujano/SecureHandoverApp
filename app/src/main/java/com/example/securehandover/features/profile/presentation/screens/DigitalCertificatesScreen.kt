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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.R
import com.example.securehandover.core.components.buttons.AppButton
import com.example.securehandover.core.components.headers.ScreenHeaderIndicator
import com.example.securehandover.core.components.navigation.AppNavigationBar
import com.example.securehandover.core.components.navigation.BottomNavItem
import com.example.securehandover.core.components.scaffold.AppScaffold
import com.example.securehandover.core.ui.theme.*

@Composable
fun DigitalCertificatesScreen(
    onBackClick: () -> Unit = {},
    onGoToHandoversClick: () -> Unit = {},
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
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // Header Indicator
            ScreenHeaderIndicator(
                stepTitle = "PERFIL / DOCUMENTOS",
                showRoleBadge = false
            )

            // Title & Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Mis actas digitales",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "El respaldo de cada entrega que completes.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )
            }

            // Empty State Card
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
                        painter = painterResource(id = R.drawable.ic_id_card),
                        contentDescription = null,
                        tint = ActionGreen,
                        modifier = Modifier.size(26.dp)
                    )

                    Text(
                        text = "Aún no tienes actas",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )

                    Text(
                        text = "Cuando completes tu primer traspaso, aquí aparecerá su acta digital con las evidencias de la entrega.",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText,
                        lineHeight = 16.sp
                    )
                }
            }

            // Active Handover Info Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Resume)
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Tienes 1 traspaso activo",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )

                    Text(
                        text = "Sony α7 IV · SH-02481",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = PrimaryText
                    )

                    Text(
                        text = "El acta estará disponible al finalizar el protocolo.",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText
                    )
                }
            }

            // Action Button
            AppButton(
                text = "Ver mis traspasos",
                onClick = onGoToHandoversClick,
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
