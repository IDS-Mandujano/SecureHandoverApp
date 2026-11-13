package com.example.securehandover.features.onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.securehandover.features.onboarding.data.datasource.OnboardingLocalDataSource
import com.example.securehandover.features.onboarding.domain.model.OnboardingPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class OnboardingUiState(
    val currentScreen: Int = 0,
    val screens: List<OnboardingPage> = emptyList(),
    val isCompleted: Boolean = false
)

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val localDataSource: OnboardingLocalDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    init {
        loadPages()
    }

    private fun loadPages() {
        val pages = localDataSource.getOnboardingPages()
        _uiState.update { it.copy(screens = pages) }
    }

    fun onNextClicked(onFinish: () -> Unit) {
        val currentState = _uiState.value
        if (currentState.currentScreen < currentState.screens.size - 1) {
            _uiState.update { it.copy(currentScreen = it.currentScreen + 1) }
        } else {
            _uiState.update { it.copy(isCompleted = true) }
            onFinish()
        }
    }

    fun onScreenChanged(index: Int) {
        _uiState.update { it.copy(currentScreen = index) }
    }
}