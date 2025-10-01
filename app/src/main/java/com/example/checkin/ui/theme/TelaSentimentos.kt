package com.example.checkin.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TelaSentimentos() {
    val scrollState = rememberScrollState()
    var energia by remember { mutableFloatStateOf(0.6f) }
    var estresse by remember { mutableFloatStateOf(0.3f) }
    var sono by remember { mutableStateOf(0.7f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF004AAD))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF8B61C2))
                .padding(20.dp)
                .verticalScroll(scrollState)
        ) {

            // Cabeçalho
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "Como estão",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFB1E4A3) // Verde limão
                    )
                    Text(
                        text = "as coisas hoje ?",
                        fontSize = 20.sp,
                        color = Color(0xFF1D3B79) // Azul escuro
                    )
                }

                Text("✕", fontSize = 24.sp, color = Color(0xFF1D3B79))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Emoções
            TituloSeccao("Como você está se sentindo?", Color(0xFF50F1B2))
            EmojiGrid(
                listOf(
                    "😡" to "Cansado(a)", "😄" to "Animado(a)", "😫" to "Abatido(a)", "😠" to "Irritado(a)", "😊" to "Feliz",
                    "🤒" to "Doente", "📱" to "Cansado(a)", "😔" to "Triste", "🤔" to "Curioso(a)", "😌" to "Tranquilo(a)"
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Nível de energia
            TituloSeccao("Nível de energia e disposição", Color(0xFF50F1B2))
            SliderCustom(energia) { energia = it }

            Spacer(modifier = Modifier.height(24.dp))

            // Carga de estresse
            TituloSeccao("Carga de estresse", Color(0xFF008DF2))
            EmojiGrid(
                listOf(
                    "🤯" to "Sobrecarregado", "😰" to "Em alerta", "😐" to "Preocupado", "🙂" to "De boa"
                )
            )
            SliderCustom(estresse) { estresse = it }

            Spacer(modifier = Modifier.height(24.dp))

            // Nível de sono
            TituloSeccao("Nível de cansaço e sono", Color(0xFF50F1B2))
            SliderCustom(sono) { sono = it }

            Spacer(modifier = Modifier.height(24.dp))

            // Capacidade de concentração
            TituloSeccao("Capacidade de concentração", Color(0xFF00D4B2))
            EmojiGrid(
                listOf(
                    "🚀" to "A milhão", "🧘" to "Mantendo bem", "🙂" to "Indo", "😴" to "Meio dormindo", "🫥" to "Sem pensar"
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun TituloSeccao(texto: String, corFundo: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(corFundo, RoundedCornerShape(8.dp))
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Text(texto, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1D3B79))
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun SliderCustom(valor: Float, aoMudar: (Float) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("←", fontSize = 20.sp, color = Color.White)
        Slider(
            value = valor,
            onValueChange = aoMudar,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.Green,
                inactiveTrackColor = Color.White.copy(alpha = 0.3f)
            )
        )
        Text("→", fontSize = 20.sp, color = Color.White)
    }
}

@Composable
fun EmojiGrid(pares: List<Pair<String, String>>) {
    var selecionado by remember { mutableStateOf<String?>(null) }

    Column {
        pares.chunked(5).forEach { linha ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                linha.forEach { (emoji, label) ->
                    val isSelecionado = selecionado == emoji

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .clip(RoundedCornerShape(50))
                                .background(if (isSelecionado) Color(0xFF50F1B2) else Color(0xFFB08FFF))
                                .clickable { selecionado = emoji },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = emoji,
                                fontSize = 28.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(label, fontSize = 12.sp, color = Color.White)
                    }
                }
            }
        }
    }
}
