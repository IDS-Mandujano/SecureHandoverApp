package com.example.securehandover.features.auth.presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.securehandover.core.components.scaffold.AppScaffold
import com.example.securehandover.core.navigation.Screen
import com.example.securehandover.core.ui.theme.PrimaryText
import com.example.securehandover.core.ui.theme.SecondaryText
import com.example.securehandover.features.auth.presentation.screens.LoginScreen
import com.example.securehandover.features.auth.presentation.screens.RegisterScreen
import com.example.securehandover.features.auth.presentation.screens.VerifyOtpScreen
import com.example.securehandover.features.auth.presentation.viewmodel.AuthViewModel
import com.example.securehandover.features.onboarding.presentation.screens.OnboardingScreen
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth

fun NavGraphBuilder.onboardingGraph(navController: NavController){
    composable(route = Screen.Onboarding.route){
        AppScaffold(
            showBackButton = false
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                OnboardingScreen(
                    onFinishOnboarding = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

fun NavGraphBuilder.authGraph(
    navController: NavController,
    supabaseClient: SupabaseClient
){
    composable(route = Screen.Login.route){
        val viewModel: AuthViewModel = hiltViewModel()

        AppScaffold(
            showBackButton = false
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                LoginScreen(
                    viewModel = viewModel,
                    onNavigateToHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onNavigateToRegister = {
                        navController.navigate(Screen.Register.route)
                    }
                )
            }
        }
    }

    composable(route = Screen.Register.route){
        val viewModel: AuthViewModel = hiltViewModel()

        AppScaffold(
            showBackButton = true,
            onBackClick = {
                viewModel.clearError()
                navController.popBackStack()
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                RegisterScreen(
                    viewModel = viewModel,
                    onNavigateToVerify = { email ->
                        navController.navigate(Screen.VerifyOtp.createRoute(email))
                    },
                    onNavigateToLogin = {
                        viewModel.clearError()
                        navController.popBackStack()
                    },
                    onNavigateToHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Register.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }

    composable(
        route = Screen.VerifyOtp.route,
        arguments = listOf(navArgument("email") { type = NavType.StringType })
    ) { backStackEntry ->
        val email = backStackEntry.arguments?.getString("email") ?: ""
        val parentEntry = remember(backStackEntry) {
            runCatching { navController.getBackStackEntry(Screen.Register.route) }.getOrNull()
        }
        val viewModel: AuthViewModel = if (parentEntry != null) {
            hiltViewModel(parentEntry)
        } else {
            hiltViewModel()
        }
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(uiState.isSuccess) {
            if (uiState.isSuccess) {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Register.route) { inclusive = true }
                }
            }
        }

        AppScaffold(
            showBackButton = true,
            onBackClick = {
                viewModel.resetVerificationState()
                navController.popBackStack()
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                VerifyOtpScreen(
                    email = email,
                    isLoading = uiState.isLoading,
                    errorMessage = uiState.errorMessage,
                    onVerifyClicked = { digits ->
                        viewModel.onVerifyClicked(email, digits)
                    },
                    onBackToLogin = {
                        viewModel.resetVerificationState()
                        navController.popBackStack(Screen.Login.route, false)
                    },
                    onBackClicked = {
                        viewModel.resetVerificationState()
                        navController.popBackStack()
                    }
                )
            }
        }
    }

    composable(route = Screen.Home.route) {
        val viewModel: AuthViewModel = hiltViewModel()

        com.example.securehandover.features.home.presentation.screens.HomeScreen(
            viewModel = viewModel,
            onBackClick = {
                navController.popBackStack()
            },
            onLogoutClick = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            },
            onProductClick = { _ ->
                navController.navigate(Screen.ProductDetail.route)
            }
        )
    }

    composable(route = Screen.ProductDetail.route) {
        com.example.securehandover.features.product.presentation.screens.ProductDetailScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onMakeOfferClick = {
                // Handle make offer action
            }
        )
    }
}
