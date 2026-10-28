package com.example.securehandover.core.components.badges

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppStatusTag(
    text : String,
    modifier: Modifier = Modifier,
    iconPainter: Painter? = null,
    containerColor : Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor : Color = MaterialTheme.colorScheme.onPrimaryContainer,
    borderColor : Color? = null,
    iconSize : Dp = 14.dp
    ){

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50.dp),
        color = containerColor,
        border = borderColor?.let { BorderStroke(1.dp, it) }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (iconPainter != null){
                Icon(
                    painter = iconPainter,
                    contentDescription = null,
                    modifier = Modifier.size(iconSize),
                    tint = contentColor
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = text,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = contentColor
                )
            }
        }
    }

}