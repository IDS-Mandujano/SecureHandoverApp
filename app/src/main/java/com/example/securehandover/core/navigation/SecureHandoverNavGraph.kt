package com.example.securehandover.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.securehandover.core.ui.theme.ActionGreen
import com.example.securehandover.features.auth.presentation.navigation.authGraph
import com.example.securehandover.features.auth.presentation.navigation.onboardingGraph
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionStatus

@Composable
fun SecureHandoverNavGraph(
    navController: NavHostController = rememberNavController(),
    supabaseClient: SupabaseClient
) {
    var startRouteState by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        supabaseClient.auth.sessionStatus.collect { status ->
            if (startRouteState == null) {
                startRouteState = when (status) {
                    is SessionStatus.Authenticated -> Screen.Home.route
                    is SessionStatus.NotAuthenticated -> Screen.Onboarding.route
                    else -> null
                }
            }
        }
    }

    if (startRouteState == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = ActionGreen)
        }
        return
    }

    NavHost(
        navController = navController,
        startDestination = startRouteState!!
    ) {
        onboardingGraph(navController)
        authGraph(
            navController = navController,
            supabaseClient = supabaseClient
        )
    }
}
