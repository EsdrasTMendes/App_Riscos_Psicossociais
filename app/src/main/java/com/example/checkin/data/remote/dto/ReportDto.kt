package com.example.checkin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ReportResponseItem(
    val id: String,
    val empresaId: String,
    val usuarioAnonimoId: String,
    val semanaReferencia: String,
    val dataAnalise: String,
    val diagnosticos: DiagnosticosDto
)

data class DiagnosticosDto(
    @SerializedName("NivelCargaTrabalho")
    val nivelCargaTrabalho: String,
    @SerializedName("DiagnosticoClimaRelacionamento")
    val diagnosticoClimaRelacionamento: String
)