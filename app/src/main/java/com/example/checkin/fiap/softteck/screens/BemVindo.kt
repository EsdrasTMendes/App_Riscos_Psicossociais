package com.example.checkin.fiap.softteck.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun CustomTextField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    icon: ImageVector,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text(placeholder) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF88D499),
            unfocusedContainerColor = Color(0xFF88D499),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            cursorColor = Color.Black,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedLeadingIconColor = Color.DarkGray,
            unfocusedLeadingIconColor = Color.DarkGray,
            focusedPlaceholderColor = Color.DarkGray,
            unfocusedPlaceholderColor = Color.DarkGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

@Composable
fun BemVindo(onStartClick: () -> Unit = {}) {
    val emojis = listOf("👩‍🦰", "👩🏽", "👩🏻‍🦱", "👨🏾", "👩🏼‍🎤", "👱‍♀️", "🧑🏻‍🦳", "👧🏽")

    // Estados para os campos de texto
    var apelido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    // Estado para mostrar popup de erro
    var showErrorPopup by remember { mutableStateOf(false) }

    // Quando showErrorPopup for true
    if (showErrorPopup) {
        LaunchedEffect(Unit) {
            delay(3000)
            showErrorPopup = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8B61C2))
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Título
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(color = Color(0xFF1D3B79), fontSize = 24.sp)) {
                        append("Primeiro\n")
                    }
                    withStyle(SpanStyle(color = Color(0xFF88D499), fontSize = 22.sp)) {
                        append("vamos escolher seu emoji")
                    }
                },
                textAlign = TextAlign.Center
            )


            Text(
                text = "Escolha um emoji:",
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                emojis.chunked(4).forEach { row ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        row.forEach { emoji ->
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                            ) {
                                Text(text = emoji, fontSize = 28.sp)
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Campos de entrada
            CustomTextField(
                text = apelido,
                onTextChange = { apelido = it },
                placeholder = "Crie um apelido",
                icon = Icons.Default.Person
            )
            CustomTextField(
                text = email,
                onTextChange = { email = it },
                placeholder = "Email",
                icon = Icons.Default.Email
            )
            CustomTextField(
                text = senha,
                onTextChange = { senha = it },
                placeholder = "Senha",
                icon = Icons.Default.Lock,
                isPassword = true
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    // Validação dos campos
                    if (apelido.isBlank() || email.isBlank() || senha.isBlank()) {
                        showErrorPopup = true
                    } else {
                        onStartClick()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF88D499)),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Começar já", color = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.White)
            }
        }

        // Popup de erro no centro da tela
        if (showErrorPopup) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF8B61C2))
                    .clickable { showErrorPopup = false }, // Fecha se clicar fora
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Red, shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = "Preencha todos os campos!",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
