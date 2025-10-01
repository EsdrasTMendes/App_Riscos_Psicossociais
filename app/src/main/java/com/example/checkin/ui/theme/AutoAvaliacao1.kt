package com.example.checkin.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkin.R

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
fun TituloSeccaoAuto(texto: String, corFundo: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(corFundo, RoundedCornerShape(16.dp))
            .padding(vertical = 10.dp, horizontal = 16.dp)
    ) {
        Text(
            texto,
            fontSize = 18.sp,
            color = Color(0xFF88d499),
            fontWeight = FontWeight.SemiBold,
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun SliderCustomAuto(valor: Float, aoMudar: (Float) -> Unit) {
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
                        color = Color.White,
                        fontWeight = FontWeight.Bold
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

@Composable
fun BotaoVoltar(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFF1f214a)),
//        border = BorderStroke(2.dp, Color.White),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.height(50.dp)
    ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Text("Voltar", color = Color.White)
    }
}

@Composable
fun BotaoSeguir(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF88d499)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.height(50.dp)
    ) {
        Text("Próximo", color = Color.Black)
        Spacer(modifier = Modifier.width(8.dp))
        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Próximo", tint = Color.Black)
    }
}

@Composable
fun AutoAvaliacao1() {
    val scrollState = rememberScrollState()
    var cargaTrabalho by remember { mutableFloatStateOf(0.0f) }
    var impactoBemEstar by remember { mutableStateOf<Int?>(null) }
    var relacionamento by remember { mutableStateOf<Int?>(null) }

    val akzidenzGrotesk = FontFamily(Font(R.font.akzidenzgroteskblack))
    val sourceserif = FontFamily(Font(R.font.sourceserif))

    Column(
        modifier = Modifier
            .background(Color(0xFF004aad))
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .padding(20.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF23255d))
                .padding(24.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Como estão",
                fontSize = 34.sp,
                fontFamily = akzidenzGrotesk,
                color = Color(0xFF8b61c2)
            )

            Text(
                text = "as coisas com você?",
                fontSize = 30.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF88d499),
                fontFamily = sourceserif,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1f214a))
                    .padding(16.dp)
            ) {
                TituloSeccaoAuto("Como você percebe sua carga de trabalho?", Color(0xFF23255d))
                SliderCustomAuto(cargaTrabalho) { cargaTrabalho = it }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1f214a))
                    .padding(16.dp)
            ) {
                TituloSeccaoAuto(
                    "Você sente que sua carga de trabalho impacta seu bem-estar e qualidade de vida?",
                    Color(0xFF23255d)
                )
                BolinhasNotaComRotulo(
                    total = 2,
                    selecionado = impactoBemEstar,
                    onSelecionar = { impactoBemEstar = it },
                    rotulos = listOf("Sim", "Não")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1f214a))
                    .padding(16.dp)
            ) {
                TituloSeccaoAuto("Como você avalia seu relacionamento com os colegas de trabalho?", Color(0xFF23255d))
                BolinhasNotaComRotulo(
                    total = 5,
                    selecionado = relacionamento,
                    onSelecionar = { relacionamento = it },
                    rotulos = listOf("Péssimo", "Ruim", "Médio", "Bom", "Ótimo")
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BotaoVoltar(onClick = { /* ação de voltar */ })
                BotaoSeguir(onClick = { /* ação de seguir */ })
            }
        }
    }
}
