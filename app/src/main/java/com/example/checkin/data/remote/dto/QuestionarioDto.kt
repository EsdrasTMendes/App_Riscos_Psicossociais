package com.example.checkin.data.remote.dto

import com.google.gson.annotations.SerializedName

data class QuestionnaireModelResponse(
    @SerializedName("_id") val id: String,
    val versao: String,
    val nome: String,
    val ativo: Boolean,
    val categorias: List<CategoryDto>
)
data class CategoryDto(
    val nome: String,
    val perguntas: List<QuestionDto>
)
data class QuestionDto(
    @SerializedName("_id") val id: String,
    val texto: String,
    val tipo: String
)

data class QuestionnaireAnswersRequest(
    val respostas: RespostasQuestionarioDto
)
data class RespostasQuestionarioDto(
    val relacionamentos: Int,
    val apoioGestor: Int,
    val cargaTrabalho: Int,
    val clarezaFuncao: Int,
    val segurancaPercebida: Int
)