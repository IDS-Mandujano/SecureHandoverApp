package com.example.securehandover.core.navigation

sealed class Screen (val route: String) {
    object Onboarding : Screen("onboarding_screen")
    object Auth : Screen("auth_screen")
    object VerifyOtp : Screen("verify_otp/{email}"){
        fun createRoute(email : String) = "verify_otp/$email"
    }
    object Home : Screen("Home")
}