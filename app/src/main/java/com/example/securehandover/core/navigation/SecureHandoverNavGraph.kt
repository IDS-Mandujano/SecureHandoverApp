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
    val sessionStatus by supabaseClient.auth.sessionStatus.collectAsState()
    var isInitialized by remember { mutableStateOf(false) }

    val startRoute = remember {
        when (sessionStatus) {
            is SessionStatus.Authenticated -> Screen.Home.route
            else -> Screen.Onboarding.route
        }
    }

    LaunchedEffect(sessionStatus) {
        if (!isInitialized) {
            if (sessionStatus !is SessionStatus.Initializing) {
                isInitialized = true
            }
        } else {
            val currentRoute = navController.currentDestination?.route
            when (sessionStatus) {
                is SessionStatus.Authenticated -> {
                    if (currentRoute != Screen.Home.route) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                }
                is SessionStatus.NotAuthenticated -> {
                    if (currentRoute == Screen.Home.route) {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                }
                else -> {}
            }
        }
    }

    if (!isInitialized) {
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
        startDestination = startRoute
    ) {
        onboardingGraph(navController)
        authGraph(
            navController = navController,
            supabaseClient = supabaseClient
        )
    }
}
