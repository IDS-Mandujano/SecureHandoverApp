package com.example.securehandover.features.auth.data.datasource

import com.example.securehandover.BuildConfig
import com.example.securehandover.features.auth.data.model.AuthDto

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
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
            username = user.userMetadata?.get("username")?.toString()?.replace("\"", ""),
            email = user.email.orEmpty()
        )
    }

    suspend fun signUp(usernameInput: String, emailInput: String, passwordInput: String) : AuthDto {
        val metadata = buildJsonObject {
            put("username", usernameInput)
        }

        val userInfo = supabaseClient.auth.signUpWith(Email) {
            email = emailInput
            password = passwordInput
            data = metadata
        }

        // Si el usuario ya existe y su correo está confirmado, identities suele estar vacío o confirmado
        val identities = userInfo?.identities
        if (identities != null && identities.isEmpty()) {
            throw Exception("El correo ya está registrado. Por favor inicia sesión con tu contraseña o con Google.")
        }

        val user = supabaseClient.auth.currentUserOrNull() ?: userInfo
            ?: throw Exception("No se pudo obtener el usuario después de registro")

        return AuthDto (
            id = user.id,
            username = usernameInput,
            email = user.email.orEmpty()
        )
    }

    suspend fun verifyOtp(emailInput: String, codeInput: String): AuthDto {

        supabaseClient.auth.verifyEmailOtp(
            type = OtpType.Email.EMAIL,
            email = emailInput,
            token = codeInput
        )

        val user = supabaseClient.auth.currentUserOrNull()
            ?: throw Exception("No se pudo verificar \n Intenta nuevamente")

        return AuthDto(
            id = user.id,
            username = user.userMetadata?.get("username")?.toString()?.replace("\"", ""),
            email = user.email.orEmpty()
        )
    }

    suspend fun signInWithGoogle() {
        val redirectUrl = BuildConfig.SUPABASE_REDIRECT_URL
        supabaseClient.auth.signInWith(Google, redirectUrl = redirectUrl)
    }

    suspend fun logout() {
        supabaseClient.auth.signOut()
    }

    suspend fun getCurrentUserOrNull(): AuthDto? {
        val user = supabaseClient.auth.currentUserOrNull() ?: return null
        return AuthDto(
            id = user.id,
            username = user.userMetadata?.get("username")?.toString()?.replace("\"", ""),
            email = user.email.orEmpty()
        )
    }
}
