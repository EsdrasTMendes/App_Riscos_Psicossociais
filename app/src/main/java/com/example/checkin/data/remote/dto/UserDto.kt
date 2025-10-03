package com.example.checkin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @SerializedName("newpassword")
    val newPassword: String
)