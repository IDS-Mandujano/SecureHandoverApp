package com.example.securehandover.features.onboarding.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.securehandover.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import com.example.securehandover.core.components.buttons.AppButton
import com.example.securehandover.core.ui.theme.ActionGreen
import com.example.securehandover.core.ui.theme.Background
import com.example.securehandover.core.ui.theme.Surface
import com.example.securehandover.core.ui.theme.TextOnboarding
import com.example.securehandover.core.ui.theme.TrustGreen
import com.example.securehandover.features.onboarding.presentation.viewmodel.OnboardingViewModel

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onFinishOnboarding: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { uiState.screens.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.currentScreen) {
        if (pagerState.currentPage != uiState.currentScreen) {
            pagerState.animateScrollToPage(uiState.currentScreen)
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        viewModel.onScreenChanged(pagerState.currentPage)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(45.dp))

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) { index ->
                val page = uiState.screens[index]

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(390.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = TrustGreen),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = page.imageResId),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(
                                    RoundedCornerShape(
                                        topStart = 24.dp,
                                        topEnd = 24.dp
                                    )
                                )
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(horizontal = 20.dp, vertical = 14.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = page.iconResId),
                                    contentDescription = null,
                                    tint = Surface,
                                    modifier = Modifier.size(24.dp)
                                )

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = page.title,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Surface
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = page.description,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TextOnboarding
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(uiState.screens.size) { iteration ->
                    val color = if (uiState.currentScreen == iteration) {
                        ActionGreen
                    } else {
                        MaterialTheme.colorScheme.outlineVariant
                    }
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(if (uiState.currentScreen == iteration) 24.dp else 8.dp, 8.dp)
                    )
                }
            }

            val buttonText = if (uiState.currentScreen == uiState.screens.size - 1) "Comenzar" else "Siguiente"

            AppButton(
                text = buttonText,
                onClick = {
                    if (uiState.currentScreen < uiState.screens.size - 1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(uiState.currentScreen + 1)
                        }
                    } else {
                        viewModel.onNextClicked(onFinishOnboarding)
                    }
                },
                height = 54.dp,
                iconPainter = painterResource(id = R.drawable.ic_arrow_rigth),
                containerColor = ActionGreen,
                contentColor = Surface
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}