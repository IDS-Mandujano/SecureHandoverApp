package com.example.securehandover.features.onboarding.data.datasource

import com.example.securehandover.R
import com.example.securehandover.features.onboarding.domain.model.OnboardingPage
import javax.inject.Inject

class OnboardingLocalDataSource @Inject constructor() {
    fun getOnboardingPages(): List<OnboardingPage> {
        return listOf(
            OnboardingPage(
                title = "Compra y vende artículos de alto valor de forma segura",
                description = "Identidad verificada, pago en custodia y evidencia de cada entrega.",
                imageResId = R.drawable.img_onboarding_browse,
                iconResId = R.drawable.ic_shield_check
            ),
            OnboardingPage(
                title = "Entrega certificada con GPS, biometría, fotos y voz",
                description = "GPS y biometría confirman que ambas personas llegan al lugar acordado antes de liberar el pago.",
                imageResId = R.drawable.img_onboarding_capture,
                iconResId = R.drawable.ic_fingerprint
            ),
            OnboardingPage(
                title = "Pago en custodia liberado únicamente al completar TODO el protocolo",
                description = "Pago en custodia para liberar el monto solo cuando el trato se completa.",
                imageResId = R.drawable.img_onboarding_handover,
                iconResId = R.drawable.ic_lock_keyhole
            )
        )
    }
}