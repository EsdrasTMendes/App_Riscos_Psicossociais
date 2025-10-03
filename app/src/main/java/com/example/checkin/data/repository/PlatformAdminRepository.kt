package com.example.checkin.data.repository

import com.example.checkin.data.local.UserPreferencesRepository
import com.example.checkin.data.remote.ApiService
import com.example.checkin.data.remote.dto.RegisterAdminRequest
import com.example.checkin.data.remote.dto.ResetPasswordRequest
import kotlinx.coroutines.flow.first

class PlatformAdminRepository(
    private val apiService: ApiService,
    private val prefs: UserPreferencesRepository
) {
    suspend fun registerAdmin(request: RegisterAdminRequest): Result<String> {
        return try {
            val token = prefs.getAuthToken.first()
                ?: return Result.failure(Exception("Token de autenticação não encontrado."))

            val response = apiService.registerAdmin("Bearer $token", request)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Falha ao registrar novo admin. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun resetPassword(userEmail: String, newPassword: String): Result<String> {
        return try {
            val token = prefs.getAuthToken.first()
                ?: return Result.failure(Exception("Token de autenticação não encontrado."))

            val request = ResetPasswordRequest(newPassword = newPassword)
            val response = apiService.resetPassword("Bearer $token", userEmail, request)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Falha ao resetar senha. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}