package com.example.checkin.models

enum class TipoPergunta {
    SLIDER,
    BOLINHAS,
}

data class Pergunta(
    val id: Int,
    val texto: String,
    val tipo: com.example.checkin.models.TipoPergunta,
    val alternativas: List<String>? = null
)