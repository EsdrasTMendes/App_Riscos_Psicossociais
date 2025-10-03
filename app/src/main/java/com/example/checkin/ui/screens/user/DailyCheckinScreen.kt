package com.example.checkin.ui.screens.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyCheckinScreen(
    navController: NavController,
    viewModel: DailyCheckinViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    // Diálogo para sucesso ou erro
    uiState.successMessage?.let {
        ResultDialog(
            title = "Obrigado!",
            message = it,
            onDismiss = {
                viewModel.clearMessages()
                navController.popBackStack()
            }
        )
    }
    uiState.error?.let {
        ResultDialog(
            title = "Erro",
            message = it,
            onDismiss = { viewModel.clearMessages() }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Check-in Diário") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            CheckinSlider(
                label = "Humor (1=Péssimo, 5=Ótimo)",
                value = viewModel.humor,
                onValueChange = { viewModel.humor = it },
                steps = 3, // 5 posições: 1, 2, 3, 4, 5
                valueRange = 1f..5f
            )
            CheckinSlider(
                label = "Horas de Sono",
                value = viewModel.sonoHoras,
                onValueChange = { viewModel.sonoHoras = it },
                steps = 15, // Permite incrementos de 0.5
                valueRange = 4f..12f
            )
            CheckinSlider(
                label = "Nível de Estresse (1=Baixo, 5=Alto)",
                value = viewModel.estresse,
                onValueChange = { viewModel.estresse = it },
                steps = 3,
                valueRange = 1f..5f
            )
            // Continue para os outros sliders...
            CheckinSlider(label = "Apetite (1=Baixo, 5=Alto)", value = viewModel.apetite, onValueChange = { viewModel.apetite = it }, steps = 3, valueRange = 1f..5f)
            CheckinSlider(label = "Concentração (1=Baixa, 5=Alta)", value = viewModel.concentracao, onValueChange = { viewModel.concentracao = it }, steps = 3, valueRange = 1f..5f)
            CheckinSlider(label = "Fadiga (1=Baixa, 5=Alta)", value = viewModel.fadiga, onValueChange = { viewModel.fadiga = it }, steps = 3, valueRange = 1f..5f)

            OutlinedTextField(
                value = viewModel.observacoes,
                onValueChange = { viewModel.observacoes = it },
                label = { Text("Observações (opcional)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Button(
                onClick = { viewModel.submitCheckin() },
                enabled = !uiState.isLoading,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                } else {
                    Text("Enviar Check-in")
                }
            }
        }
    }
}

@Composable
private fun CheckinSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    steps: Int,
    valueRange: ClosedFloatingPointRange<Float>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Slider(
                value = value,
                onValueChange = onValueChange,
                valueRange = valueRange,
                steps = steps,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = if (valueRange == 1f..5f) value.roundToInt().toString() else "%.1f".format(value),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(40.dp)
            )
        }
    }
}

@Composable
private fun ResultDialog(title: String, message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = { TextButton(onClick = onDismiss) { Text("OK") } }
    )
}