package com.example.securehandover.features.auth.presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.securehandover.core.components.scaffold.AppScaffold
import com.example.securehandover.core.navigation.Screen
import com.example.securehandover.features.auth.presentation.screens.LoginScreen
import com.example.securehandover.features.auth.presentation.screens.RegisterScreen
import com.example.securehandover.features.auth.presentation.screens.VerifyOtpScreen
import com.example.securehandover.features.auth.presentation.viewmodel.AuthViewModel
import com.example.securehandover.features.handovers.presentation.screens.HandoversListScreen
import com.example.securehandover.features.identity.presentation.screens.IdentityVerificationStep1Screen
import com.example.securehandover.features.identity.presentation.screens.IdentityVerificationStep2Screen
import com.example.securehandover.features.listing.presentation.screens.CreateListingScreen
import com.example.securehandover.features.listing.presentation.screens.ListingPreviewScreen
import com.example.securehandover.features.offer.presentation.screens.MakeOfferScreen
import com.example.securehandover.features.onboarding.presentation.screens.OnboardingScreen
import com.example.securehandover.features.protocol.presentation.screens.HandoverCertificateScreen
import com.example.securehandover.features.protocol.presentation.screens.HandoverProtocolGpsScreen
import com.example.securehandover.features.protocol.presentation.screens.HandoverProtocolStep2Screen
import com.example.securehandover.features.protocol.presentation.screens.HandoverProtocolStep3Screen
import com.example.securehandover.features.protocol.presentation.screens.HandoverProtocolStep4Screen
import io.github.jan.supabase.SupabaseClient

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
    var isIdentityVerified by mutableStateOf(false)

    fun handleBottomNav(targetRoute: String) {
        when (targetRoute) {
            "explorar" -> {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = false }
                    launchSingleTop = true
                }
            }
            "traspasos" -> {
                navController.navigate(Screen.HandoversList.route) {
                    popUpTo(Screen.Home.route) { inclusive = false }
                    launchSingleTop = true
                }
            }
            "publicar" -> {
                navController.navigate(Screen.CreateListing.route) {
                    popUpTo(Screen.Home.route) { inclusive = false }
                    launchSingleTop = true
                }
            }
            "perfil" -> {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            }
        }
    }

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
            },
            onNavigateToRoute = { route -> handleBottomNav(route) }
        )
    }

    composable(route = Screen.HandoversList.route) {
        HandoversListScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onContinueProtocolClick = {
                navController.navigate(Screen.HandoverProtocolGps.route)
            },
            onNavigateToRoute = { route -> handleBottomNav(route) }
        )
    }

    composable(route = Screen.CreateListing.route) {
        CreateListingScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onPreviewClick = {
                navController.navigate(Screen.ListingPreview.route)
            },
            onSaveDraftClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = false }
                }
            },
            onNavigateToRoute = { route -> handleBottomNav(route) }
        )
    }

    composable(route = Screen.ListingPreview.route) {
        ListingPreviewScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onPublishClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = false }
                }
            },
            onEditClick = {
                navController.popBackStack()
            },
            onNavigateToRoute = { route -> handleBottomNav(route) }
        )
    }

    composable(route = Screen.ProductDetail.route) {
        com.example.securehandover.features.product.presentation.screens.ProductDetailScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onMakeOfferClick = {
                if (isIdentityVerified) {
                    navController.navigate(Screen.MakeOffer.route)
                } else {
                    navController.navigate(Screen.IdentityStep1.route)
                }
            }
        )
    }

    composable(route = Screen.IdentityStep1.route) {
        IdentityVerificationStep1Screen(
            onBackClick = {
                navController.popBackStack()
            },
            onScanIneClick = {
                navController.navigate(Screen.IdentityStep2.route)
            }
        )
    }

    composable(route = Screen.IdentityStep2.route) {
        IdentityVerificationStep2Screen(
            onBackClick = {
                navController.popBackStack()
            },
            onStartSelfieClick = {
                isIdentityVerified = true
                navController.navigate(Screen.MakeOffer.route) {
                    popUpTo(Screen.ProductDetail.route) { inclusive = false }
                }
            }
        )
    }

    composable(route = Screen.MakeOffer.route) {
        MakeOfferScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onReviewOfferClick = {
                navController.navigate(Screen.HandoverProtocolGps.route)
            }
        )
    }

    composable(route = Screen.HandoverProtocolGps.route) {
        HandoverProtocolGpsScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onContinueClick = {
                navController.navigate(Screen.ProtocolStep2.route)
            }
        )
    }

    composable(route = Screen.ProtocolStep2.route) {
        HandoverProtocolStep2Screen(
            onBackClick = {
                navController.popBackStack()
            },
            onContinueClick = {
                navController.navigate(Screen.ProtocolStep3.route)
            }
        )
    }

    composable(route = Screen.ProtocolStep3.route) {
        HandoverProtocolStep3Screen(
            onBackClick = {
                navController.popBackStack()
            },
            onContinueClick = {
                navController.navigate(Screen.ProtocolStep4.route)
            }
        )
    }

    composable(route = Screen.ProtocolStep4.route) {
        HandoverProtocolStep4Screen(
            onBackClick = {
                navController.popBackStack()
            },
            onFinishHandoverClick = {
                navController.navigate(Screen.ProtocolStep5.route)
            }
        )
    }

    composable(route = Screen.ProtocolStep5.route) {
        HandoverCertificateScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onReturnHomeClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            }
        )
    }
}
