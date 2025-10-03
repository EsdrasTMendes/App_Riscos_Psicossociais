package com.example.checkin.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferencesRepository(private val context: Context) {

    private val authTokenKey = stringPreferencesKey("auth_token")
    private val userRoleKey = stringPreferencesKey("user_role")
    private val empresaIdKey = stringPreferencesKey("empresa_id")

    suspend fun saveAuthData(token: String, role: String, empresaId: String?) {
        context.dataStore.edit { preferences ->
            preferences[authTokenKey] = token
            preferences[userRoleKey] = role

            if(empresaId != null) {
                preferences[empresaIdKey] = empresaId
            } else {
                preferences.remove(empresaIdKey)
            }
        }
    }

    val getAuthToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[authTokenKey]
        }

    val getUserRole: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[userRoleKey]
        }

    val getEmpresaId: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[empresaIdKey]
        }

    suspend fun clearAuthData() {
        context.dataStore.edit { preferences ->
            preferences.remove(authTokenKey)
            preferences.remove(userRoleKey)
        }
    }
}