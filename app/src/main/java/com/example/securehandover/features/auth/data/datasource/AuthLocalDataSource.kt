package com.example.securehandover.features.auth.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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
            preferences[USER_NAME_KEY] = entity.username
            preferences[USER_EMAIL_KEY] = entity.email
        }
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
            preferences.clear()
        }
    }
}