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
fun WeeklyQuestionnaireScreen(
    navController: NavController,
    viewModel: WeeklyQuestionnaireViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    uiState.submissionSuccessMessage?.let {
        ResultDialog(title = "Obrigado!", message = it, onDismiss = {
            viewModel.clearMessages()
            navController.popBackStack()
        })
    }
    uiState.error?.let {
        ResultDialog(title = "Erro", message = it, onDismiss = { viewModel.clearMessages() })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Questionário Semanal") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar")
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
                label = "Relacionamentos no Trabalho",
                value = viewModel.relacionamentos,
                onValueChange = { viewModel.relacionamentos = it },
                steps = 9,
                valueRange = 1f..10f
            )
            CheckinSlider(
                label = "Apoio do Gestor",
                value = viewModel.apoioGestor,
                onValueChange = { viewModel.apoioGestor = it },
                steps = 9,
                valueRange = 1f..10f
            )
            CheckinSlider(
                label = "Carga de Trabalho",
                value = viewModel.cargaTrabalho,
                onValueChange = { viewModel.cargaTrabalho = it },
                steps = 9,
                valueRange = 1f..10f
            )
            CheckinSlider(
                label = "Clareza da Função",
                value = viewModel.clarezaFuncao,
                onValueChange = { viewModel.clarezaFuncao = it },
                steps = 9,
                valueRange = 1f..10f
            )
            CheckinSlider(
                label = "Segurança Percebida",
                value = viewModel.segurancaPercebida,
                onValueChange = { viewModel.segurancaPercebida = it },
                steps = 9,
                valueRange = 1f..10f
            )

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { viewModel.submitAnswers() },
                enabled = !uiState.isSubmitting,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (uiState.isSubmitting) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                } else {
                    Text("Enviar Questionário")
                }
            }
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
                text = value.roundToInt().toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(40.dp)
            )
        }
    }
}