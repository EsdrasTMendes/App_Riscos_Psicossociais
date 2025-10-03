package com.example.checkin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterCompanyRequest(
    @SerializedName("nome")
    val name: String,
    val adminUsername: String,
    val adminPassword: String,
    val setores: List<String>
)

data class RegisterCompanyResponse(
    val status: String,
    val message: String,
    val empresaId: String,
    val registrationLink: String
)