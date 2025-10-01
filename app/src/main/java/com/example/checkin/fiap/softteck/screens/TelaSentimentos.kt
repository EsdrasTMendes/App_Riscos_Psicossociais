package com.example.checkin.fiap.softteck.screens

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun TelaSentimentos(navController: NavController) {
    val scrollState = rememberScrollState()
    var energia by remember { mutableFloatStateOf(0.6f) }
    var estresse by remember { mutableFloatStateOf(0.3f) }
    var sono by remember { mutableFloatStateOf(0.7f) }

    val sentimentosSelecionados = remember { mutableStateListOf<String>() }
    var mostrarPopup by remember { mutableStateOf(false) }
    var mensagemErro by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF004AAD))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
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
                        Text("Como estão", fontSize = 26.sp, color = Color(0xFFB1E4A3))
                        Text("as coisas hoje ?", fontSize = 20.sp, color = Color(0xFF1D3B79))
                    }
                    Text(
                        "✕",
                        fontSize = 24.sp,
                        color = Color(0xFF1D3B79),
                        modifier = Modifier.clickable {
                            navController.popBackStack() // Fecha a tela ao clicar no X
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                TituloSecao("Como você está se sentindo?", Color(0xFF88D499))
                EmojiGrid(
                    listOf(
                        "😡" to "Cansado(a)", "😄" to "Animado(a)", "😫" to "Abatido(a)", "😠" to "Irritado(a)", "😊" to "Feliz",
                        "🤒" to "Doente", "😴" to "Cansado(a)", "😔" to "Triste", "🤔" to "Curioso(a)", "😌" to "Tranquilo(a)"
                    ),
                    sentimentosSelecionados
                )

                Spacer(modifier = Modifier.height(20.dp))

                TituloSecao("Nível de energia e disposição", Color(0xFF88D499))
                SliderCustom(energia) { energia = it }

                Spacer(modifier = Modifier.height(24.dp))

                TituloSecao("Carga de estresse", Color(0xFF88D499))
                EmojiGrid(
                    listOf(
                        "🤯" to "Sobrecarregado", "😰" to "Em alerta", "😐" to "Preocupado", "🙂" to "De boa"
                    ),
                    sentimentosSelecionados
                )
                SliderCustom(estresse) { estresse = it }

                Spacer(modifier = Modifier.height(24.dp))

                TituloSecao("Nível de cansaço e sono", Color(0xFF88D499))
                SliderCustom(sono) { sono = it }

                Spacer(modifier = Modifier.height(24.dp))

                TituloSecao("Capacidade de concentração", Color(0xFF88D499))
                EmojiGrid(
                    listOf(
                        "🚀" to "A milhão", "🧘" to "Mantendo bem", "🙂" to "Indo", "😴" to "Meio lento", "🫥" to "Sem pensar"
                    ),
                    sentimentosSelecionados
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            if (mensagemErro.isNotEmpty()) {
                Text(
                    text = mensagemErro,
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Button(
                onClick = {
                    if (sentimentosSelecionados.isEmpty()) {
                        mensagemErro = "Selecione pelo menos um sentimento"
                    } else {
                        mensagemErro = ""
                        mostrarPopup = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF88D499),
                    contentColor = Color(0xFF1D3B79)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Fazer Check-in", fontSize = 18.sp)
            }
        }

        if (mostrarPopup) {

            LaunchedEffect(Unit) {
                delay(3000)
                mostrarPopup = false
                navController.navigate("HomeScreen") {
                    popUpTo("TelaSentimentos") { inclusive = true }
                }
            }

            Dialog(onDismissRequest = { mostrarPopup = false }) {
                Box(
                    modifier = Modifier
                        .size(220.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "✅ Check-in feito!",
                        fontSize = 18.sp,
                        color = Color(0xFF1D3B79)
                    )
                }
            }
        }
    }
}

@Composable
fun TituloSecao(texto: String, corFundo: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(corFundo, RoundedCornerShape(8.dp))
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Text(texto, fontSize = 16.sp, color = Color(0xFF1D3B79))
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun SliderCustom(valor: Float, aoMudar: (Float) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
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
fun EmojiGrid(pares: List<Pair<String, String>>, selecionados: MutableList<String>) {
    Column {
        pares.chunked(5).forEach { linha ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                linha.forEach { (emoji, label) ->
                    val isSelecionado = selecionados.contains(emoji)

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .clip(RoundedCornerShape(50))
                                .background(if (isSelecionado) Color(0xFF50F1B2) else Color(0xFFB08FFF))
                                .clickable {
                                    if (isSelecionado) {
                                        selecionados.remove(emoji)
                                    } else {
                                        selecionados.add(emoji)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(emoji, fontSize = 28.sp)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(label, fontSize = 12.sp, color = Color.White)
                    }
                }
            }
        }
    }
}
