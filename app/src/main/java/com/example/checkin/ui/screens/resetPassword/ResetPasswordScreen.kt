package com.example.checkin.ui.screens.resetPassword

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
fun ResetPasswordScreen(
    navController: NavController,
    resetPasswordViewModel: ResetPasswordViewModel
) {
    val uiState by resetPasswordViewModel.uiState.collectAsState()

    // Diálogo de resultado
    uiState.successMessage?.let {
        ResultDialog(
            title = "Sucesso!",
            message = it,
            onDismiss = {
                resetPasswordViewModel.clearMessages()
                navController.popBackStack() // Volta para o painel
            }
        )
    }
    uiState.error?.let {
        ResultDialog(
            title = "Erro",
            message = it,
            onDismiss = { resetPasswordViewModel.clearMessages() }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resetar Senha de Usuário") },
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
                value = resetPasswordViewModel.userEmail,
                onValueChange = resetPasswordViewModel::onUserEmailChange,
                placeholder = "Email do usuário a ser alterado",
                icon = Icons.Default.Email
            )
            ModernTextField(
                value = resetPasswordViewModel.newPassword,
                onValueChange = resetPasswordViewModel::onNewPasswordChange,
                placeholder = "Nova Senha",
                icon = Icons.Default.Lock,
                isPassword = true
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { resetPasswordViewModel.resetPassword() },
                enabled = !uiState.isLoading,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                } else {
                    Text("Resetar Senha")
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