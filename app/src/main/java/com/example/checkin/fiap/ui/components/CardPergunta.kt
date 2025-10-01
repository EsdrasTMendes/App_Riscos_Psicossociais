package com.example.checkin.fiap.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardPergunta(texto: String, corFundo: Color) {
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