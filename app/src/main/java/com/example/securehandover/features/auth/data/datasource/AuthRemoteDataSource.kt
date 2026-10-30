package com.example.securehandover.features.auth.data.datasource

import com.example.securehandover.features.auth.data.model.AuthDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val supabaseClient: SupabaseClient
){
    suspend fun signIn(emailInput: String, passwordInput: String) : AuthDto {
        supabaseClient.auth.signInWith(Email){
            email = emailInput
            password = passwordInput
        }

        val user = supabaseClient.auth.currentUserOrNull()
            ?: throw Exception("No se pudo obtener el usuario")

        return AuthDto(
            id = user.id,
            username = user.userMetadata?.get("username")?.toString(),
            email = user.email.orEmpty()
        )
    }

    suspend fun signUp(usernameInput: String, emailInput: String, passwordInput: String) : AuthDto {

        supabaseClient.auth.signUpWith(Email){
            email = emailInput
            password = passwordInput
        }

        val user = supabaseClient.auth.currentUserOrNull()
            ?: throw Exception("No se pudo otener al usuario despues de registro")

        return AuthDto (
            id = user.id,
            username = usernameInput,
            email = user.email.orEmpty()
        )
    }

    suspend fun signInWhitGoogle() : AuthDto {
        supabaseClient.auth.signInWith(Google)

        val user = supabaseClient.auth.currentUserOrNull()
            ?: throw Exception("No se pudo autenticar con Google Auth")

        return AuthDto(
            id = user.id,
            username = user.userMetadata?.get("username")?.toString(),
            email = user.email.orEmpty()
        )
    }

    suspend fun logout() {
        supabaseClient.auth.signOut()
    }
}