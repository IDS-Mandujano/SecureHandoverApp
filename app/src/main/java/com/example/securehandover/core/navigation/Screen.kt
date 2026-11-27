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
    object IdentityStep1 : Screen("identity_step_1")
    object IdentityStep2 : Screen("identity_step_2")
    object MakeOffer : Screen("make_offer")
    object HandoverProtocolGps : Screen("handover_protocol_gps")
    object ProtocolStep2 : Screen("protocol_step_2")
    object ProtocolStep3 : Screen("protocol_step_3")
    object ProtocolStep4 : Screen("protocol_step_4")
    object ProtocolStep5 : Screen("protocol_step_5")
    object HandoversList : Screen("handovers_list")
    object ListingPreview : Screen("listing_preview")
}
