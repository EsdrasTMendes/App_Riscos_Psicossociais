package com.example.checkin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BolinhasNotaComRotulo(
    total: Int,
    selecionado: Int?,
    onSelecionar: (Int) -> Unit,
    rotulos: List<String>
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(total) { index ->
                val selecionadoAgora = index == selecionado
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(if (selecionadoAgora) Color(0xFF88d499) else Color.Transparent)
                        .border(
                            2.dp,
                            if (selecionadoAgora) Color.White else Color(0xFF88d499),
                            CircleShape
                        )
                        .clickable { onSelecionar(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = " ",
                        color = Color.White
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            rotulos.forEachIndexed { index, rotulo ->
                Text(
                    text = rotulo,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(50.dp),
                    fontSize = 12.sp
                )
            }
        }
    }
}
