package com.example.checkin.fiap.softteck.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun CustomField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text(placeholder) },
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
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
fun LoginScreen(
    onLoginClick: (email: String, senha: String) -> Unit = { _, _ -> },
    showError: Boolean = false,
    onDismissError: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var showEmptyFieldsError by remember { mutableStateOf(false) }

    // Esconde popup de campos vazios após 3s
    if (showEmptyFieldsError) {
        LaunchedEffect(Unit) {
            delay(3000)
            showEmptyFieldsError = false
        }
    }

    // Esconde popup de erro de login após 3s
    if (showError) {
        LaunchedEffect(Unit) {
            delay(3000)
            onDismissError()
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
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Título
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(color = Color(0xFF1D3B79), fontSize = 24.sp)) {
                        append("Efetue o login\n")
                    }
                },
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo email
            CustomField(
                text = email,
                onTextChange = { email = it },
                placeholder = "Email",
                icon = Icons.Default.Email
            )

            // Campo senha
            CustomField(
                text = senha,
                onTextChange = { senha = it },
                placeholder = "Senha",
                icon = Icons.Default.Lock,
                isPassword = true
            )

            Spacer(modifier = Modifier.weight(1f))

            // Botão entrar
            Button(
                onClick = {
                    if (email.isBlank() || senha.isBlank()) {
                        showEmptyFieldsError = true
                    } else {
                        onLoginClick(email, senha)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF88D499)),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Entrar", color = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.Lock, contentDescription = null, tint = Color.White)
            }
        }

        // Popup erro: campos vazios
        if (showEmptyFieldsError) {
            ErrorPopup("Preencha todos os campos!")
        }

        // Popup erro: login inválido
        if (showError) {
            ErrorPopup("Usuário ou senha inválidos")
        }
    }
}

@Composable
fun ErrorPopup(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color.Red, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(text = message, color = Color.White, fontSize = 16.sp)
        }
    }
}
