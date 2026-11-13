package com.example.securehandover.features.home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.securehandover.core.ui.theme.*

data class ProductItem(
    val id: String,
    val title: String,
    val price: String,
    val condition: String,
    val locationDistance: String,
    val isVerified: Boolean = true,
    val isFavorite: Boolean = false,
    val placeholderColor: Color = Color(0xFFE2E7E4)
)

@Composable
fun ProductCard(
    product: ProductItem,
    onFavoriteToggle: (String) -> Unit = {},
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var isFav by remember(product.isFavorite) { mutableStateOf(product.isFavorite) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(product.id) },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceColor),
        border = BorderStroke(1.dp, BorderColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(135.dp)
                    .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                    .background(product.placeholderColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null,
                    tint = SecondaryText.copy(alpha = 0.3f),
                    modifier = Modifier.size(42.dp)
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(SurfaceColor)
                        .clickable {
                            isFav = !isFav
                            onFavoriteToggle(product.id)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isFav) R.drawable.ic_heart_filled else R.drawable.ic_heart
                        ),
                        contentDescription = "Favorito",
                        tint = PrimaryText,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = product.condition.uppercase(),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryText,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = product.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryText
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = product.price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = PrimaryText
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = product.locationDistance,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText
                )

                if (product.isVerified) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_badge_check),
                            contentDescription = null,
                            tint = ActionGreen,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = "Identidad verificada",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = ActionGreen
                        )
                    }
                }
            }
        }
    }
}
