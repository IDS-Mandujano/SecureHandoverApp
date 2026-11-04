package com.example.securehandover.features.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    val id: String,
    val email: String,
    val username: String? = null,
    val phone: String? = null,
    @SerialName("profile_completed")
    val profileCompleted: Boolean? = null
)
