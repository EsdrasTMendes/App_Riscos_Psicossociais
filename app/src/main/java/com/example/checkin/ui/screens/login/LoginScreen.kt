package com.example.checkin.ui.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.example.checkin.data.local.UserPreferencesRepository
import com.example.checkin.data.remote.ApiClient
import com.example.checkin.data.repository.AuthRepository

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    val uiState = loginViewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(uiState.loginSuccess) {
        if (uiState.loginSuccess) {
            onLoginSuccess()
        }
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = it,
                    duration = SnackbarDuration.Short
                )
                loginViewModel.clearError()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color(0xFF8B61C2)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = "Ícone de Login",
                    tint = Color(0xFF1D3B79),
                    modifier = Modifier.size(80.dp)
                )

                Text("Bem-vindo(a) de volta!", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text("Faça login para continuar", fontSize = 16.sp, color = Color.White.copy(alpha = 0.8f))

                Spacer(modifier = Modifier.height(16.dp))

                ModernTextField(
                    value = loginViewModel.username,
                    onValueChange = { loginViewModel.onUsernameChange(it) },
                    placeholder = "Email",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    enabled = !uiState.isLoading
                )

                ModernTextField(
                    value = loginViewModel.password,
                    onValueChange = { loginViewModel.onPasswordChange(it) },
                    placeholder = "Senha",
                    icon = Icons.Default.Lock,
                    isPassword = true,
                    enabled = !uiState.isLoading
                )



                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { loginViewModel.login() },
                    enabled = !uiState.isLoading,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF88D499)),
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            color = Color(0xFF1D3B79),
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text("Entrar", color = Color(0xFF1D3B79), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = onRegisterClick) {
                    Text("Não tem uma conta? Cadastre-se", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun ModernTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    enabled: Boolean = true
) {
    var passwordVisible by remember { mutableStateOf(!isPassword) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
        singleLine = true,
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else keyboardType),
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF88D499),
            unfocusedContainerColor = Color(0xFF88D499),
            disabledContainerColor = Color(0xFF88D499).copy(alpha = 0.5f),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            cursorColor = Color.Black,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedLeadingIconColor = Color.DarkGray,
            unfocusedLeadingIconColor = Color.DarkGray,
            focusedTrailingIconColor = Color.DarkGray,
            unfocusedTrailingIconColor = Color.DarkGray,
            focusedPlaceholderColor = Color.DarkGray,
            unfocusedPlaceholderColor = Color.DarkGray
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val fakeRepo = AuthRepository(ApiClient.instance, UserPreferencesRepository(androidx.compose.ui.platform.LocalContext.current))
    val viewModel = LoginViewModel(fakeRepo)
    LoginScreen(
        loginViewModel = viewModel,
        onLoginSuccess = {},
        onRegisterClick = {}
        )
}