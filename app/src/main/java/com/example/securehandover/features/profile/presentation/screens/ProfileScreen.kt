package com.example.securehandover.features.profile.presentation.screens

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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun ProfileScreen(
    onBackClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
    onIdentitySecurityClick: () -> Unit = {},
    onMyListingsClick: () -> Unit = {},
    onDigitalCertificatesClick: () -> Unit = {},
    onPaymentMethodsClick: () -> Unit = {},
    onPrivacySettingsClick: () -> Unit = {},
    onHelpCenterClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
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
                stepTitle = "13 / Perfil",
                showRoleBadge = false
            )

            // Title & Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "La confianza tiene nombre.",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "Administra tu identidad, tus artículos y la seguridad de tu cuenta.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )
            }

            // User Info Card
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
                    Text(
                        text = "Diego M.",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )

                    Text(
                        text = "Roma Norte, CDMX",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText
                    )

                    AppStatusTag(
                        text = "Identidad verificada",
                        iconPainter = painterResource(id = R.drawable.ic_badge_check),
                        containerColor = ExitColor,
                        contentColor = ActionGreen,
                        cornerRadius = 8.dp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    OutlinedButton(
                        onClick = onEditProfileClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, BorderColor),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = SurfaceColor,
                            contentColor = PrimaryText
                        )
                    ) {
                        Text(
                            text = "Editar perfil",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            // Reputation Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(TrustGreen)
                    .padding(18.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Tu reputación se construye.",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Text(
                            text = "Sin calificaciones todavía",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )

                        Text(
                            text = "Al finalizar tu primera entrega, podrás recibir y dar una calificación.",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = TextOnboarding,
                            lineHeight = 16.sp
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "1",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Traspaso activo",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Normal,
                                color = TextOnboarding
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "0",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Entregas certificadas",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Normal,
                                color = TextOnboarding
                            )
                        }
                    }
                }
            }

            // Menu Items Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                border = BorderStroke(1.dp, BorderColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    ProfileMenuItem(
                        icon = painterResource(id = R.drawable.ic_shield_check),
                        title = "Identidad y seguridad",
                        subtitle = "Identidad verificada · revisar los datos",
                        onClick = onIdentitySecurityClick
                    )
                    HorizontalDivider(color = BorderColor, thickness = 0.8.dp)
                    ProfileMenuItem(
                        icon = painterResource(id = R.drawable.ic_plus_circle),
                        title = "Mis publicaciones",
                        subtitle = "Administra anuncios y borradores",
                        onClick = onMyListingsClick
                    )
                    HorizontalDivider(color = BorderColor, thickness = 0.8.dp)
                    ProfileMenuItem(
                        icon = painterResource(id = R.drawable.ic_id_card),
                        title = "Mis actas digitales",
                        subtitle = "Documentos de tus entregas certificadas",
                        onClick = onDigitalCertificatesClick
                    )
                    HorizontalDivider(color = BorderColor, thickness = 0.8.dp)
                    ProfileMenuItem(
                        icon = painterResource(id = R.drawable.ic_lock_keyhole),
                        title = "Métodos de pago",
                        subtitle = "Débito terminación 2048",
                        onClick = onPaymentMethodsClick
                    )
                }
            }

            // Secondary Action Buttons
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedButton(
                    onClick = onPrivacySettingsClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, BorderColor),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = SurfaceColor,
                        contentColor = PrimaryText
                    )
                ) {
                    Text(
                        text = "Privacidad y notificaciones",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                OutlinedButton(
                    onClick = onHelpCenterClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, BorderColor),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = SurfaceColor,
                        contentColor = PrimaryText
                    )
                ) {
                    Text(
                        text = "Centro de ayuda",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Text(
                    text = "Cerrar sesión",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = RiskText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onLogoutClick() }
                        .padding(vertical = 6.dp)
                )

                Text(
                    text = "Perfil y datos ficticios · SecureHandover",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ProfileMenuItem(
    icon: Painter,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = ActionGreen,
                modifier = Modifier.size(22.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryText
                )

                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText
                )
            }
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_rigth),
            contentDescription = null,
            tint = SecondaryText,
            modifier = Modifier.size(16.dp)
        )
    }
}
