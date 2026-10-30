package com.example.securehandover.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.securehandover.features.auth.presentation.navigation.authGraph
import com.example.securehandover.features.auth.presentation.navigation.onboardingGraph

@Composable
fun SecureHandoverNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding.route
    ) {
        onboardingGraph(navController)
        authGraph(navController)
    }
}