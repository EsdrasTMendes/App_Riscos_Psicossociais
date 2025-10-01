package com.example.checkin.data////package com.example.checkin.data

import com.example.checkin.models.Pergunta
import com.example.checkin.models.TipoPergunta

class FakePerguntaRepository {
   fun getPerguntas(): List<Pergunta> {
       return listOf(
           Pergunta(
               id = 1,
                texto = "Como você se sentiu hoje no trabalho?",
                tipo = TipoPergunta.SLIDER
           ),
           Pergunta(
                id = 2,
                texto = "Qual dessas palavras melhor descreve seu dia?",
                tipo = TipoPergunta.BOLINHAS,
                alternativas = listOf("Produtivo", "Estressante", "Leve", "Confuso")
            ),
            Pergunta(
                id = 3,
                texto = "Você sentiu que teve apoio da equipe?",
                tipo = TipoPergunta.BOLINHAS,
                alternativas = listOf("Não", "SIM")
            )
       )
    }
}