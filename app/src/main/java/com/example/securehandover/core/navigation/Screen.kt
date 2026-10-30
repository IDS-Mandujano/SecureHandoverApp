package com.example.securehandover.core.navigation

sealed class Screen (val route: String) {
    object Onboarding : Screen("onboarding_screen")
    object Auth : Screen("auth_screen")
}