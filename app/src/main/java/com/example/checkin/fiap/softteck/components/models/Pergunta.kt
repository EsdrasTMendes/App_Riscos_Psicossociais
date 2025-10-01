package com.example.checkin.fiap.softteck.components.models

import com.example.checkin.models.TipoPergunta

enum class TipoPergunta {
    SLIDER,
    BOLINHAS,
}

data class Pergunta(
    val id: Int,
    val texto: String,
    val tipo: TipoPergunta,
    val alternativas: List<String>? = null
)