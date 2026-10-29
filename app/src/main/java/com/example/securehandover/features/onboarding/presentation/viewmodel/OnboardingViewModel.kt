package com.example.securehandover.features.onboarding.presentation.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.example.securehandover.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class OnboardingScreen (
    val title : String,
    val description : String,
    @DrawableRes val imageResId: Int,
    @DrawableRes val iconResId : Int
)

data class OnboardingUiState (
    val currentScreen : Int = 0,
    val screens : List<OnboardingScreen> = listOf(
        OnboardingScreen (
            title = "Compra y vende artículos de alto valor de forma segura",
            description = "Identidad verificada, pago en custodia y evidencia de cada entrega.",
            imageResId = R.drawable.img_onboarding_browse,
            iconResId = R.drawable.ic_shield_check
        ),
        OnboardingScreen (
            title = "Entrega certificada con GPS, biometría, fotos y voz",
            description = "GPS y biometría confirman que ambas personas llegan al lugar acordado antes de liberar el pago.",
            imageResId = R.drawable.img_onboarding_capture,
            iconResId = R.drawable.ic_fingerprint
        ),
        OnboardingScreen (
            title = "Pago en custodia liberado únicamente al completar TODO el protocolo",
            description = "Pago en custodia para liberar el monto solo cuando el trato se completa.",
            imageResId = R.drawable.img_onboarding_handover,
            iconResId = R.drawable.ic_lock_keyhole
        )
    ),
    val isCompleted : Boolean = false
)

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel(){

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState : StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    fun onNextClicked(onFinish : ()-> Unit) {
        val currentState = _uiState.value
        if (currentState.currentScreen < currentState.screens.size -1){
            _uiState.update { it.copy(currentScreen = it.currentScreen + 1) }
        } else {
            _uiState.update { it.copy(isCompleted = true) }
            onFinish()
        }
    }

    fun onScreenChanged(index : Int) {
        _uiState.update { it.copy(currentScreen = index) }
    }

}