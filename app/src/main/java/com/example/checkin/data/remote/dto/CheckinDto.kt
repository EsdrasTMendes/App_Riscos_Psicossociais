package com.example.checkin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DailyCheckinRequest(
    val respostas: RespostasCheckinDto,
    val observacoes: String?
)

data class RespostasCheckinDto(
    val humor: Int,
    @SerializedName("sonoHoras")
    val sonoHoras: Float,
    val estresse: Int,
    val apetite: Int,
    val concentracao: Int,
    val fadiga: Int
)