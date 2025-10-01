package com.example.checkin.fiap.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.checkin.models.TipoPergunta

@Composable
fun PerguntaDinamica(
    titulo: String,
    tipo: TipoPergunta,
    corFundo: Color = Color(0xFF1f214a),
    valorSlider: Float = 0f,
    onChangeSlider: ((Float) -> Unit)? = null,
    selecionado: Int? = null,
    onSelecionar: ((Int) -> Unit)? = null,
    rotulos: List<String> = emptyList()
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(corFundo)
            .padding(16.dp)
    ) {
        CardPergunta(titulo, Color(0xFF23255d))

        when (tipo) {
            TipoPergunta.SLIDER -> {
                if (onChangeSlider != null) {
                    SliderPerguntas(valorSlider, onChangeSlider)
                }
            }

            TipoPergunta.BOLINHAS -> {
                if (onSelecionar != null && rotulos.isNotEmpty()) {
                    RadioPerguntas(
                        total = rotulos.size,
                        selecionado = selecionado,
                        onSelecionar = onSelecionar,
                        rotulos = rotulos
                    )
                }
            }
        }
    }
}