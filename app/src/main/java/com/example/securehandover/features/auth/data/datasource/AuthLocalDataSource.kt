package com.example.securehandover.features.auth.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.securehandover.features.auth.data.model.AuthEntity
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    suspend fun saveUser(entity: AuthEntity){
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = entity.id
            if (entity.username.isNotBlank()) {
                preferences[USER_NAME_KEY] = entity.username
            }
            preferences[USER_EMAIL_KEY] = entity.email
        }
    }

    suspend fun getActiveUsername(): String? {
        val preferences = dataStore.data.first()
        return preferences[USER_NAME_KEY]?.takeIf { it.isNotBlank() }
    }

    suspend fun saveProfileForEmail(email: String, username: String, phone: String) {
        if (email.isBlank()) return
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey("profile_email_$email")] = email
            if (username.isNotBlank()) {
                preferences[stringPreferencesKey("profile_username_$email")] = username
            }
            preferences[stringPreferencesKey("profile_phone_$email")] = phone
            preferences[booleanPreferencesKey("profile_completed_$email")] = true
        }
    }

    data class LocalProfile(
        val username: String,
        val phone: String,
        val profileCompleted: Boolean
    )

    suspend fun getProfileForEmail(email: String): LocalProfile? {
        if (email.isBlank()) return null
        val preferences = dataStore.data.first()
        val completed = preferences[booleanPreferencesKey("profile_completed_$email")] ?: false
        if (!completed) return null
        return LocalProfile(
            username = preferences[stringPreferencesKey("profile_username_$email")] ?: "",
            phone = preferences[stringPreferencesKey("profile_phone_$email")] ?: "",
            profileCompleted = completed
        )
    }

    suspend fun getUser(): AuthEntity? {
        val preferences = dataStore.data.first()
        val id = preferences[USER_ID_KEY] ?: return null
        val username = preferences[USER_NAME_KEY] ?: ""
        val email = preferences[USER_EMAIL_KEY] ?: ""

        return AuthEntity(id = id, username = username, email = email)
    }

    suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.remove(USER_ID_KEY)
            preferences.remove(USER_NAME_KEY)
            preferences.remove(USER_EMAIL_KEY)
        }
    }
}
