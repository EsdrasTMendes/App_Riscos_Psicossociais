package com.example.checkin.network

import retrofit2.Call
import retrofit2.http.*

/**
 * Backend confirmado: usa "username" + "password".
 * Para compatibilidade, enviamos também "email" com o mesmo valor.
 */
data class LoginRequest(
    val username: String,
    val password: String,
    val email: String? = null
)

data class Empresa(
    val empresaId: String,
    val nome: String
)

interface ApiService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<String> // token puro (String)

    @GET("empresas")
    fun getEmpresas(@Header("Authorization") token: String): Call<List<Empresa>>
}
