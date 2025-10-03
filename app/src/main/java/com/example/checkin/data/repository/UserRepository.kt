package com.example.checkin.data.repository

import com.example.checkin.data.local.UserPreferencesRepository
import com.example.checkin.data.remote.ApiService
import com.example.checkin.data.remote.dto.DailyCheckinRequest
import com.example.checkin.data.remote.dto.QuestionnaireAnswersRequest
import com.example.checkin.data.remote.dto.QuestionnaireModelResponse
import kotlinx.coroutines.flow.first

class UserRepository(
    private val apiService: ApiService,
    private val prefs: UserPreferencesRepository
) {
    suspend fun submitCheckin(request: DailyCheckinRequest): Result<String> {
        return try {
            val token = prefs.getAuthToken.first()
                ?: return Result.failure(Exception("Token não encontrado."))

            val response = apiService.submitDailyCheckin("Bearer $token", request)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Falha ao enviar check-in. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getActiveQuestionnaire(): Result<QuestionnaireModelResponse> {
        return try {
            val token = prefs.getAuthToken.first()
                ?: return Result.failure(Exception("Token não encontrado."))

            val response = apiService.getActiveQuestionnaire("Bearer $token")

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Falha ao buscar questionário. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun submitQuestionnaire(request: QuestionnaireAnswersRequest): Result<String> {
        return try {
            val token = prefs.getAuthToken.first()
                ?: return Result.failure(Exception("Token não encontrado."))

            val response = apiService.submitQuestionnaireAnswers("Bearer $token", request)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Falha ao enviar respostas. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}