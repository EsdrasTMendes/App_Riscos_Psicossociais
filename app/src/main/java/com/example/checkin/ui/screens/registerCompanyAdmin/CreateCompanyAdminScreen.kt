package com.example.checkin.ui.screens.registerCompanyAdmin

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.checkin.ui.screens.login.ModernTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCompanyAdminScreen(
    navController: NavController,
    viewModel: CreateCompanyAdminViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    // Diálogo para sucesso ou erro
    uiState.successMessage?.let {
        ResultDialog(
            title = "Sucesso!",
            message = it,
            onDismiss = {
                viewModel.clearMessages()
                navController.popBackStack() // Volta para o painel
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
                title = { Text("Criar Novo Admin da Empresa") },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ModernTextField(
                value = viewModel.username,
                onValueChange = viewModel::onUsernameChange,
                placeholder = "Email do novo admin",
                icon = Icons.Default.Email
            )
            ModernTextField(
                value = viewModel.password,
                onValueChange = viewModel::onPasswordChange,
                placeholder = "Senha do novo admin",
                icon = Icons.Default.Lock,
                isPassword = true
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { viewModel.createAdmin() },
                enabled = !uiState.isLoading,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                } else {
                    Text("Criar Admin")
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