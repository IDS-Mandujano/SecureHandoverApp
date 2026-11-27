package com.example.securehandover.core.components.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.securehandover.core.ui.theme.ActionGreen
import com.example.securehandover.core.ui.theme.SecondaryText

data class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter = selectedIcon
)

@Composable
fun AppNavigationBar(
    items: List<BottomNavItem>,
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    selectedColor: Color = ActionGreen,
    unselectedColor: Color = SecondaryText,
    containerColor: Color = Color.White
) {
    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        onNavigate(item.route)
                    }
                },
                icon = {
                    Icon(
                        painter = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(text = item.label)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = selectedColor,
                    selectedTextColor = selectedColor,
                    unselectedIconColor = unselectedColor,
                    unselectedTextColor = unselectedColor,
                    indicatorColor = selectedColor.copy(alpha = 0.12f)
                )
            )
        }
    }
}
