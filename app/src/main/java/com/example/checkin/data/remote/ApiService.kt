package com.example.checkin.data.remote

import com.example.checkin.data.remote.dto.DailyCheckinRequest
import com.example.checkin.data.remote.dto.LoginRequest
import com.example.checkin.data.remote.dto.LoginResponse
import com.example.checkin.data.remote.dto.QuestionnaireAnswersRequest
import com.example.checkin.data.remote.dto.QuestionnaireModelResponse
import com.example.checkin.data.remote.dto.RegisterCompanyRequest
import com.example.checkin.data.remote.dto.RegisterCompanyResponse
import com.example.checkin.data.remote.dto.RegisterAdminRequest
import com.example.checkin.data.remote.dto.ReportResponseItem
import com.example.checkin.data.remote.dto.ResetPasswordRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.Path


interface ApiService {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/empresas")
    suspend fun registerCompany(
        @Header("Authorization") token: String,
        @Body request: RegisterCompanyRequest
    ): Response<RegisterCompanyResponse>

    @POST("api/platform-admin/users")
    suspend fun registerAdmin(
        @Header("Authorization") token: String,
        @Body request: RegisterAdminRequest
    ): Response<String>

    @POST("api/users/{email}/reset-password")
    suspend fun resetPassword(
        @Header("Authorization") token: String,
        @Path("email") userEmail: String,
        @Body request: ResetPasswordRequest
    ): Response<String>

    @POST("api/empresas/{empresaId}/admins")
    suspend fun createCompanyAdmin(
        @Header("Authorization") token: String,
        @Path("empresaId") empresaId: String,
        @Body request: RegisterAdminRequest
    ): Response<String>

    @GET("api/relatorios/empresa")
    suspend fun getCompanyReports(
        @Header("Authorization") token: String
    ): Response<List<ReportResponseItem>>

    @POST("api/auth/register/{empresaId}/user")
    suspend fun registerUser(
        @Path("empresaId") empresaId: String,
        @Body request: RegisterAdminRequest
    ): Response<String>

    @POST("api/checkins")
    suspend fun submitDailyCheckin(
        @Header("Authorization") token: String,
        @Body request: DailyCheckinRequest
    ): Response<String>

    @GET("api/questionarios/modelo/ativo")
    suspend fun getActiveQuestionnaire(
        @Header("Authorization") token: String
    ): Response<QuestionnaireModelResponse>

    @POST("api/questionarios/respostas")
    suspend fun submitQuestionnaireAnswers(
        @Header("Authorization") token: String,
        @Body request: QuestionnaireAnswersRequest
    ): Response<String>
}