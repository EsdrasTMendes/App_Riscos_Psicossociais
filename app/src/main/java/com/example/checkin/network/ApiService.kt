package com.example.checkin.network

import retrofit2.Call
import retrofit2.http.*

data class LoginRequest(
    val username: String,
    val password: String,
    val email: String? = null
)

data class EmpresaRequest(
    val name: String,
    val adminUsername: String,
    val adminPassword: String,
    val setores: Array<String>
)

data class Empresa(
    val empresaId: String,
    val nome: String
)

interface ApiService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<String>

    @POST("/api/empresas")
    fun createEmpresa(@Body request: EmpresaRequest, @Header("Authorization") token: String): Call<String>


    @GET("empresas")
    fun getEmpresas(@Header("Authorization") token: String): Call<List<Empresa>>


}
