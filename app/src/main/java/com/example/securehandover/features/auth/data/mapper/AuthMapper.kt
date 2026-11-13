package com.example.securehandover.features.auth.data.mapper

import com.example.securehandover.features.auth.data.model.AuthDto
import com.example.securehandover.features.auth.data.model.AuthEntity
import com.example.securehandover.features.auth.domain.model.User

fun AuthDto.toDomain(): User {
    return User(
        id = id,
        username = username ?: email.substringBefore("@"),
        email = email
    )
}

fun AuthEntity.toDomain(): User {
    return User(
        id = id,
        username = username,
        email = email
    )
}

fun User.toEntity(): AuthEntity {
    return AuthEntity(
        id = id,
        username = username,
        email = email
    )
}