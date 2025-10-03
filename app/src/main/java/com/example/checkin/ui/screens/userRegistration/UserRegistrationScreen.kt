package com.example.checkin.ui.screens.userRegistration

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
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
fun UserRegistrationScreen(
    navController: NavController,
    viewModel: UserRegistrationViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    uiState.successMessage?.let {
        ResultDialog(
            title = "Sucesso!",
            message = "$it\nAgora você pode fazer o login.",
            onDismiss = {
                viewModel.clearMessages()
                navController.popBackStack()
            }
        )
    }
    uiState.error?.let {
        ResultDialog(
            title = "Erro no Cadastro",
            message = it,
            onDismiss = { viewModel.clearMessages() }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadastro de Usuário") },
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
                value = viewModel.companyId,
                onValueChange = viewModel::onCompanyIdChange,
                placeholder = "ID da Empresa",
                icon = Icons.Default.Business
            )
            ModernTextField(
                value = viewModel.username,
                onValueChange = viewModel::onUsernameChange,
                placeholder = "Seu Email",
                icon = Icons.Default.Email
            )
            ModernTextField(
                value = viewModel.password,
                onValueChange = viewModel::onPasswordChange,
                placeholder = "Crie uma Senha",
                icon = Icons.Default.Lock,
                isPassword = true
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { viewModel.registerUser() },
                enabled = !uiState.isLoading,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                } else {
                    Text("Cadastrar")
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