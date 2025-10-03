package com.example.checkin.data.repository

import com.example.checkin.data.local.UserPreferencesRepository
import com.example.checkin.data.remote.ApiService
import com.example.checkin.data.remote.dto.RegisterCompanyRequest
import com.example.checkin.data.remote.dto.RegisterCompanyResponse
import kotlinx.coroutines.flow.first

class CompanyRepository(
    private val apiService: ApiService,
    private val prefs: UserPreferencesRepository
) {
    suspend fun registerCompany(request: RegisterCompanyRequest): Result<RegisterCompanyResponse> {
        return try {
            val token = prefs.getAuthToken.first()
            if (token == null) {
                return Result.failure(Exception("Token de autenticação não encontrado."))
            }

            val response = apiService.registerCompany("Bearer $token", request)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Falha ao registrar empresa. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}