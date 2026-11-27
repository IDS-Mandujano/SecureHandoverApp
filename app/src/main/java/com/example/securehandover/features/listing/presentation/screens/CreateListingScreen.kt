package com.example.securehandover.features.listing.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextAlign
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
fun CreateListingScreen(
    onBackClick: () -> Unit = {},
    onPreviewClick: () -> Unit = {},
    onSaveDraftClick: () -> Unit = {},
    onNavigateToRoute: (String) -> Unit = {}
) {
    var titleInput by remember { mutableStateOf("") }
    var categoryInput by remember { mutableStateOf("") }
    var conditionInput by remember { mutableStateOf("") }
    var descriptionInput by remember { mutableStateOf("") }
    var priceInput by remember { mutableStateOf("") }
    var locationInput by remember { mutableStateOf("Roma Norte, CDMX") }

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
                stepTitle = "12 / Publicar",
                showRoleBadge = false
            )

            // Title & Subtitle
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Dale una segunda historia.",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 30.sp
                )

                Text(
                    text = "Describe tu artículo y encuentra un trato seguro cerca de ti.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )
            }

            // Photo Upload Card
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
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = null,
                        tint = PrimaryText,
                        modifier = Modifier.size(24.dp)
                    )

                    Text(
                        text = "Añadir fotos del artículo",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )

                    Text(
                        text = "Agrega de 1 a 8 fotos: frente, detalles y accesorios. Usa imágenes reales y bien iluminadas.",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText,
                        lineHeight = 16.sp
                    )

                    OutlinedButton(
                        onClick = { },
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
                            text = "Seleccionar fotos",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            // Form Field 1: Título del anuncio
            ListingFormField(
                label = "Título del anuncio",
                value = titleInput,
                onValueChange = { titleInput = it },
                placeholder = "Ej. Sony α7 IV - solo cuerpo",
                supportingText = "Incluye marca, modelo y qué vendes."
            )

            // Form Field 2: Categoría
            ListingFormField(
                label = "Categoría",
                value = categoryInput,
                onValueChange = { categoryInput = it },
                placeholder = "Selecciona una categoría",
                supportingText = "Fotografía, tecnología o colección."
            )

            // Form Field 3: Estado del artículo
            ListingFormField(
                label = "Estado del artículo",
                value = conditionInput,
                onValueChange = { conditionInput = it },
                placeholder = "Selecciona su estado",
                supportingText = "Describe con honestidad las señales de uso."
            )

            // Form Field 4: Descripción
            ListingFormField(
                label = "Descripción",
                value = descriptionInput,
                onValueChange = { descriptionInput = it },
                placeholder = "Cuéntanos sobre tu artículo",
                supportingText = "Incluye funcionamiento, accesorios y detalles."
            )

            // Form Field 5: Precio · MXN
            ListingFormField(
                label = "Precio · MXN",
                value = priceInput,
                onValueChange = { priceInput = it },
                placeholder = "Ej. 28,500",
                supportingText = "El comprador podrá enviarte una oferta."
            )

            // Form Field 6: Ubicación aproximada
            ListingFormField(
                label = "Ubicación aproximada",
                value = locationInput,
                onValueChange = { locationInput = it },
                placeholder = "Roma Norte, CDMX",
                supportingText = "No publiques tu domicilio exacto."
            )

            // Security Banner Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(TrustGreen)
                    .padding(18.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "Tu seguridad empieza aquí.",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    Text(
                        text = "Elige un punto público para la entrega. Tu identidad debe estar verificada antes de concretar el traspaso.",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextOnboarding,
                        lineHeight = 16.sp
                    )
                }
            }

            // Action Buttons
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                AppButton(
                    text = "Vista previa del anuncio",
                    onClick = onPreviewClick,
                    iconPainter = painterResource(id = R.drawable.ic_arrow_rigth),
                    containerColor = ActionGreen,
                    contentColor = SurfaceColor,
                    cornerRadius = 14.dp,
                    height = 50.dp
                )

                OutlinedButton(
                    onClick = onSaveDraftClick,
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
                        text = "Guardar borrador",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Text(
                    text = "Revisa las fotos y los datos antes de publicar.",
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
private fun ListingFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    supportingText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryText
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceColor)
                .border(BorderStroke(1.dp, BorderColor), RoundedCornerShape(12.dp))
                .padding(horizontal = 14.dp, vertical = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = PrimaryText
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText
                        )
                    }
                    innerTextField()
                }
            )
        }
        Text(
            text = supportingText,
            fontSize = 11.sp,
            fontWeight = FontWeight.Normal,
            color = SecondaryText
        )
    }
}
