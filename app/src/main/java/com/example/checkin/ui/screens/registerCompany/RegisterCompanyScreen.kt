package com.example.checkin.ui.screens.registerCompany

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.checkin.data.remote.dto.RegisterCompanyResponse
import com.example.checkin.ui.screens.login.ModernTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterCompanyScreen(
    navController: NavController,
    registerCompanyViewModel: RegisterCompanyViewModel
) {
    val uiState by registerCompanyViewModel.uiState.collectAsState()

    // Lida com a exibição do diálogo de sucesso ou erro
    uiState.successResponse?.let {
        SuccessDialog(
            response = it,
            onDismiss = {
                registerCompanyViewModel.clearMessages()
                navController.popBackStack() // Volta para a tela de admin
            }
        )
    }

    uiState.error?.let {
        ErrorDialog(
            error = it,
            onDismiss = { registerCompanyViewModel.clearMessages() }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadastrar Nova Empresa") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ModernTextField(
                value = registerCompanyViewModel.companyName,
                onValueChange = registerCompanyViewModel::onCompanyNameChange,
                placeholder = "Nome da Empresa",
                icon = Icons.Default.Business
            )
            ModernTextField(
                value = registerCompanyViewModel.adminUsername,
                onValueChange = registerCompanyViewModel::onAdminUsernameChange,
                placeholder = "Email do Admin da Empresa",
                icon = Icons.Default.Email
            )
            ModernTextField(
                value = registerCompanyViewModel.adminPassword,
                onValueChange = registerCompanyViewModel::onAdminPasswordChange,
                placeholder = "Senha do Admin",
                icon = Icons.Default.Lock,
                isPassword = true
            )
            ModernTextField(
                value = registerCompanyViewModel.setores,
                onValueChange = registerCompanyViewModel::onSetoresChange,
                placeholder = "Setores (separados por vírgula)",
                icon = Icons.Default.List
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { registerCompanyViewModel.registerCompany() },
                enabled = !uiState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                } else {
                    Text("Cadastrar Empresa")
                }
            }
        }
    }
}

@Composable
fun SuccessDialog(response: RegisterCompanyResponse, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Sucesso!") },
        text = {
            Column {
                Text(response.message)
                Spacer(modifier = Modifier.height(8.dp))
                Text("ID da Empresa:", fontWeight = FontWeight.Bold)
                Text(response.empresaId)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Link de Cadastro (compartilhe com os usuários):", fontWeight = FontWeight.Bold)
                Text(response.registrationLink)
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("OK") }
        }
    )
}

@Composable
fun ErrorDialog(error: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Erro no Cadastro") },
        text = { Text(error) },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Tentar Novamente") }
        }
    )
}