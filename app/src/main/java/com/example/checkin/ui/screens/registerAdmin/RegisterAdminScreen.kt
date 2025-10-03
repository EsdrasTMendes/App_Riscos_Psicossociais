package com.example.checkin.ui.screens.registerAdmin

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
fun RegisterAdminScreen(
    navController: NavController,
    registerAdminViewModel: RegisterAdminViewModel
) {
    val uiState by registerAdminViewModel.uiState.collectAsState()

    uiState.successMessage?.let {
        ResultDialog(
            title = "Sucesso!",
            message = it,
            onDismiss = {
                registerAdminViewModel.clearMessages()
                navController.popBackStack()
            }
        )
    }
    uiState.error?.let {
        ResultDialog(
            title = "Erro",
            message = it,
            onDismiss = { registerAdminViewModel.clearMessages() }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadastrar Novo Admin") },
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
                value = registerAdminViewModel.username,
                onValueChange = registerAdminViewModel::onUsernameChange,
                placeholder = "Email do novo admin",
                icon = Icons.Default.Email
            )
            ModernTextField(
                value = registerAdminViewModel.password,
                onValueChange = registerAdminViewModel::onPasswordChange,
                placeholder = "Senha do novo admin",
                icon = Icons.Default.Lock,
                isPassword = true
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { registerAdminViewModel.registerAdmin() },
                enabled = !uiState.isLoading,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                } else {
                    Text("Cadastrar Admin")
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