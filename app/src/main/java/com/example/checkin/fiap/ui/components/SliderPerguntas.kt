package com.example.checkin.fiap.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


fun faixaCarga(valor: Float): String = when (valor) {
    in 0f..30f -> "Baixa"
    in 31f..50f -> "Moderada"
    in 51f..80f -> "Elevada"
    else -> "Alta"
}

fun corPorIntensidade(valor: Float): Color = when (valor) {
    in 0f..30f -> Color(0xFF88d499)
    in 31f..50f -> Color(0xFFFFD700)
    in 51f..80f -> Color(0xFFFFA500)
    else -> Color(0xFFFF4500)
}

@Composable
fun SliderPerguntas(valor: Float, aoMudar: (Float) -> Unit) {
    val cor = corPorIntensidade(valor)
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = faixaCarga(valor),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = cor
        )
        Slider(
            value = valor,
            onValueChange = aoMudar,
            valueRange = 0f..100f,
            steps = 9,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = cor,
                inactiveTrackColor = Color.White.copy(alpha = 0.3f)
            )
        )
    }
}