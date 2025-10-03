package com.example.checkin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    @SerializedName("token")
    val token: String,

    @SerializedName("roles")
    val roles: List<String>,

    @SerializedName("empresaId")
    val empresaId: String?
)