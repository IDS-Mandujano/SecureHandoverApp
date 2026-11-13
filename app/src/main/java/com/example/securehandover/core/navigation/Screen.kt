package com.example.securehandover.core.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding_screen")
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    object VerifyOtp : Screen("verify_otp/{email}") {
        fun createRoute(email: String) = "verify_otp/$email"
    }
    object Home : Screen("Home")
    object ProductDetail : Screen("product_detail")
}
