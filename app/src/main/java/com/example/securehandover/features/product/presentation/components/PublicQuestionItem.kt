package com.example.securehandover.features.product.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.securehandover.core.ui.theme.*

data class QuestionData(
    val id: String,
    val userNameAndTime: String,
    val questionText: String,
    val sellerNameTag: String = "Elena R. · Vendedora",
    val sellerResponseText: String? = null
)

@Composable
fun PublicQuestionItem(
    question: QuestionData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // User Name & Time (e.g. Diego M. · Hace 20 min)
        Text(
            text = question.userNameAndTime,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = SecondaryText
        )

        // Question Text (e.g. ¿Podemos probar enfoque y grabación 4K?)
        Text(
            text = question.questionText,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryText,
            lineHeight = 18.sp
        )

        // Seller Response Box
        if (!question.sellerResponseText.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(ExitColor)
                    .padding(12.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = question.sellerNameTag,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ActionGreen
                    )

                    Text(
                        text = question.sellerResponseText,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = SecondaryText,
                        lineHeight = 17.sp
                    )
                }
            }
        }
    }
}
