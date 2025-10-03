package com.example.checkin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterAdminRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)