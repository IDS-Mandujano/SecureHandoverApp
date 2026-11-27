package com.example.securehandover.features.product.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.R
import com.example.securehandover.core.ui.theme.*

@Composable
fun SellerCard(
    sellerInitials: String = "ER",
    sellerName: String = "Elena R.",
    memberSinceAndLocation: String = "Miembro desde 2024 · Roma Norte",
    rating: String = "4.9 / 5",
    certifiedDeliveriesCount: String = "24 entregas certificadas",
    highlightsText: String = "Puntual · Producto exacto · Ubicación precisa",
    isCertified: Boolean = true,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceColor),
        border = BorderStroke(1.dp, BorderColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // First Row: Avatar, Name, Status, Certified Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(ExitColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = sellerInitials,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = ActionGreen
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = sellerName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryText
                        )

                        Text(
                            text = memberSinceAndLocation,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = SecondaryText
                        )
                    }
                }

                if (isCertified) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_badge_check),
                        contentDescription = "Certificado",
                        tint = ActionGreen,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            // Separator with margins
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                thickness = 1.dp,
                color = BorderColor
            )

            // Second Row: Rating & Certified Deliveries
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = PrimaryText,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        text = rating,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )
                }

                Text(
                    text = certifiedDeliveriesCount,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = SecondaryText
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Highlights Row
            Text(
                text = highlightsText,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = ActionGreen
            )
        }
    }
}
