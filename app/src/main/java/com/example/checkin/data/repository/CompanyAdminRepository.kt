package com.example.checkin.data.repository

import com.example.checkin.data.local.UserPreferencesRepository
import com.example.checkin.data.remote.dto.RegisterAdminRequest
import com.example.checkin.data.remote.dto.ReportResponseItem
import com.example.checkin.data.remote.ApiService
import kotlinx.coroutines.flow.first

class CompanyAdminRepository(
    private val apiService: ApiService,
    private val prefs: UserPreferencesRepository
) {
    suspend fun createCompanyAdmin(request: RegisterAdminRequest): Result<String> {
        return try {
            val token = prefs.getAuthToken.first()
            val empresaId = prefs.getEmpresaId.first()

            if (token == null || empresaId == null) {
                return Result.failure(Exception("Dados do usuário não encontrados. Faça login novamente."))
            }

            val response = apiService.createCompanyAdmin("Bearer $token", empresaId, request)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Falha ao criar admin. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getReports(): Result<List<ReportResponseItem>> {
        return try {
            val token = prefs.getAuthToken.first()
                ?: return Result.failure(Exception("Token não encontrado."))

            val response = apiService.getCompanyReports("Bearer $token")

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Falha ao buscar relatórios. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}