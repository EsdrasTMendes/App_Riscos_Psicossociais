package com.example.checkin.data.repository

import android.util.Log
import com.example.checkin.data.local.UserPreferencesRepository
import com.example.checkin.data.remote.ApiService
import com.example.checkin.data.remote.dto.LoginRequest
import com.example.checkin.data.remote.dto.RegisterAdminRequest

class AuthRepository(
    private val apiService: ApiService,
    private val prefs: UserPreferencesRepository
) {
    suspend fun login(username: String, password: String): Result<String> {
        return try {
            val response = apiService.login(LoginRequest(username, password))
            if (response.isSuccessful && response.body() != null) {
                val loginResponse = response.body()!!

                val primaryRole = loginResponse.roles.firstOrNull() ?: "USER"
                Log.d("AuthRepository", "Role primária extraída: $primaryRole")

                prefs.saveAuthData(
                    token = loginResponse.token,
                    role = primaryRole,
                    empresaId = loginResponse.empresaId
                    )

                Result.success(primaryRole)

            } else {
                Result.failure(Exception("Usuário ou senha inválidos"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun registerUser(empresaId: String, request: RegisterAdminRequest): Result<String> {
        return try {
            val response = apiService.registerUser(empresaId, request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Falha no cadastro. Verifique os dados. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}