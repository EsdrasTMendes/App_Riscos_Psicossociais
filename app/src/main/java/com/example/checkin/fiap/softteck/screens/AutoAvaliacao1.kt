package com.example.checkin.fiap.softteck.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.checkin.R
import com.example.checkin.fiap.softteck.data.FakePerguntaRepository
import com.example.checkin.models.TipoPergunta
import com.example.checkin.fiap.ui.components.PerguntaDinamica

@Composable
fun AutoAvaliacao1(navController: NavController) {
    val repository = FakePerguntaRepository()
    val perguntasMock = repository.getPerguntas()

    val respostasSlider = remember {
        mutableStateListOf<Float>().apply {
            perguntasMock.filter { it.tipo == TipoPergunta.SLIDER }.forEach { add(0f) }
        }
    }

    val respostasRadio = remember {
        mutableStateListOf<Int?>().apply {
            perguntasMock.filter { it.tipo == TipoPergunta.BOLINHAS }.forEach { add(null) }
        }
    }

    val akzidenzGrotesk = FontFamily(Font(R.font.akzidenzgroteskblack))
    val sourceserif = FontFamily(Font(R.font.sourceserif))

    Column(
        modifier = Modifier
            .background(Color(0xFF004aad))
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF23255d))
                .padding(24.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxHeight(),
                contentPadding = PaddingValues(bottom = 32.dp)
            ) {
                item {
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
                }

                itemsIndexed(perguntasMock) { index, pergunta ->
                    val sliderIndex = perguntasMock.take(index + 1).count { it.tipo == TipoPergunta.SLIDER } - 1
                    val radioIndex = perguntasMock.take(index + 1).count { it.tipo == TipoPergunta.BOLINHAS } - 1

                    PerguntaDinamica(
                        titulo = pergunta.texto,
                        tipo = pergunta.tipo,
                        rotulos = pergunta.alternativas ?: emptyList(),
                        valorSlider = if (pergunta.tipo == TipoPergunta.SLIDER) respostasSlider[sliderIndex] else 0f,
                        onChangeSlider = if (pergunta.tipo == TipoPergunta.SLIDER) {
                            { respostasSlider[sliderIndex] = it }
                        } else null,
                        selecionado = if (pergunta.tipo == TipoPergunta.BOLINHAS) respostasRadio[radioIndex] else null,
                        onSelecionar = if (pergunta.tipo == TipoPergunta.BOLINHAS) {
                            { respostasRadio[radioIndex] = it }
                        } else null
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    Button(
                        onClick = {
                            navController.navigate("HomeScreen")
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF88d499)),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Salvar", color = Color.Black)
                    }
                }
            }
        }
    }
}

