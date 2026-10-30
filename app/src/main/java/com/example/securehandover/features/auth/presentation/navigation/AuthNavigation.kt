package com.example.securehandover.features.auth.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.securehandover.core.components.scaffold.AppScaffold
import com.example.securehandover.core.navigation.Screen
import com.example.securehandover.features.auth.presentation.screens.AuthScreen
import com.example.securehandover.features.onboarding.presentation.screens.OnboardingScreen

fun NavGraphBuilder.onboardingGraph(navController: NavController){
    composable(route = Screen.Onboarding.route){
        AppScaffold(
            showBackButton = false
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                OnboardingScreen(
                    onFinishOnboarding = {
                        navController.navigate(Screen.Auth.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

fun NavGraphBuilder.authGraph(navController: NavController){
    composable(route = Screen.Auth.route){
        AppScaffold(
            showBackButton = true,
            onBackClick = {navController.popBackStack()}
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                AuthScreen(
                    onSuccessFulAuth = {},
                    onBackClick = {navController.popBackStack()}
                )
            }
        }
    }
}