package com.example.checkin.fiap.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BotaoVoltar(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFF1f214a)),
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
