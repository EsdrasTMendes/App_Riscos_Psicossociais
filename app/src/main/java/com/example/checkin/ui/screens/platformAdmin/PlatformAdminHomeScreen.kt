package com.example.checkin.ui.screens.platformAdmin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.rememberCoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlatformAdminHomeScreen(
    navController: NavController,
    onLogoutClick: ()-> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Painel do Administrador") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF8B61C2),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = onLogoutClick) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Sair",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        containerColor = Color(0xFFF4F4F8)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Bem-vindo, Admin!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            AdminActionButton(
                text = "Cadastrar Nova Empresa",
                icon = Icons.Default.AddBusiness,
                onClick = {
                    navController.navigate("RegisterCompanyScreen")
                }
            )

            AdminActionButton(
                text = "Cadastrar Novo Admin",
                icon = Icons.Default.PersonAdd,
                onClick = {
                    navController.navigate("RegisterAdminScreen")
                }
            )

            AdminActionButton(
                text = "Resetar Senha de Usuário",
                icon = Icons.Default.Password,
                onClick = {
                    navController.navigate("ResetPasswordScreen")
                }
            )
        }
    }
}

@Composable
fun AdminActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF8B61C2)
        )
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 16.sp)
    }
}