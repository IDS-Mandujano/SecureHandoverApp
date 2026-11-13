package com.example.securehandover.features.home.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.example.securehandover.core.components.navigation.AppNavigationBar
import com.example.securehandover.core.components.navigation.BottomNavItem
import com.example.securehandover.core.components.scaffold.AppScaffold
import com.example.securehandover.core.ui.theme.*
import com.example.securehandover.features.auth.presentation.viewmodel.AuthViewModel
import com.example.securehandover.features.home.presentation.components.*

@Composable
fun HomeScreen(
    viewModel: AuthViewModel? = null,
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    var selectedCategory by remember { mutableStateOf("Todo") }
    var currentNavRoute by remember { mutableStateOf("explorar") }
    val searchQuery by remember { mutableStateOf("") }

    val categories = listOf("Todo", "Fotografía", "Tecnología", "Colección")

    val sampleProducts = remember {
        listOf(
            ProductItem(
                id = "1",
                title = "Sony α7 IV",
                price = "$28,900",
                condition = "Excelente estado",
                locationDistance = "A 1.2 km · CDMX",
                isVerified = true,
                placeholderColor = Color(0xFFE2DDD7)
            ),
            ProductItem(
                id = "2",
                title = "iPhone 15 Pro",
                price = "$15,800",
                condition = "Como nuevo",
                locationDistance = "A 2.8 km · CDMX",
                isVerified = true,
                placeholderColor = Color(0xFFDCDFD8)
            ),
            ProductItem(
                id = "3",
                title = "MacBook Air M2",
                price = "$18,500",
                condition = "Muy buen estado",
                locationDistance = "A 3.1 km · CDMX",
                isVerified = true,
                placeholderColor = Color(0xFFDDDCD8)
            ),
            ProductItem(
                id = "4",
                title = "Fujifilm X100V",
                price = "$25,000",
                condition = "Excelente estado",
                locationDistance = "A 3.8 km · CDMX",
                isVerified = true,
                placeholderColor = Color(0xFFE6E3DB)
            )
        )
    }

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
                currentRoute = currentNavRoute,
                onNavigate = { route ->
                    if (route == "perfil" && viewModel != null) {
                        viewModel.logout(onLogoutClick)
                    } else {
                        currentNavRoute = route
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Screen indicator and role badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "01 / MERCADO LOCAL",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryText,
                    letterSpacing = 1.sp
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Resume)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Comprador",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SecondaryText
                    )
                }
            }

            // Headline Title and Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Valor real. Trato seguro.",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "Encuentra lo que buscas, con identidad verificada y entrega certificada.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )
            }

            // Location Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.clickable { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_map_pin),
                    contentDescription = null,
                    tint = ActionGreen,
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = "Roma Norte, CDMX",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryText
                )

                Text(
                    text = " · 5 km",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_down),
                    contentDescription = "Cambiar ubicación",
                    tint = PrimaryText,
                    modifier = Modifier.size(12.dp)
                )
            }

            // Search Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(SurfaceColor)
                    .border(BorderStroke(1.dp, BorderColor), RoundedCornerShape(14.dp))
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = null,
                            tint = SecondaryText,
                            modifier = Modifier.size(18.dp)
                        )

                        Text(
                            text = if (searchQuery.isEmpty()) "Cámaras, tecnología y más" else searchQuery,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = if (searchQuery.isEmpty()) SecondaryText else PrimaryText
                        )
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.ic_menu_filter),
                        contentDescription = "Filtros",
                        tint = PrimaryText,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Category Chips
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 2.dp)
            ) {
                items(categories) { category ->
                    CategoryChip(
                        text = category,
                        isSelected = category == selectedCategory,
                        onClick = { selectedCategory = category }
                    )
                }
            }

            // Trust Alert Banner
            TrustBanner()

            // Catalog Section Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cerca de ti",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryText
                )

                Text(
                    text = "Precios en MXN",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText
                )
            }

            // Catalog Grid (2 columns layout)
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                sampleProducts.chunked(2).forEach { rowProducts ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowProducts.forEach { product ->
                            ProductCard(
                                product = product,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        if (rowProducts.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            // Pro Subscription Banner
            ProBanner()

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
