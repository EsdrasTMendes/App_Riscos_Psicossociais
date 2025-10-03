package com.example.checkin.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.checkin.data.repository.AuthRepository
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val error: String? = null,
    val userRole: String? = null // <-- CAMPO NOVO PARA GUARDAR A ROLE
)

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {

    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onUsernameChange(newUsername: String) {
        username = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun login() {
        if (username.isBlank() || password.isBlank()) {
            uiState = uiState.copy(error = "Preencha todos os campos!")
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            val result = repository.login(username, password)

            // 2. EM CASO DE SUCESSO, GUARDAMOS A ROLE NO ESTADO
            result.onSuccess { role ->
                uiState = uiState.copy(
                    isLoading = false,
                    loginSuccess = true,
                    userRole = role // <-- GUARDAMOS A ROLE AQUI
                )
            }.onFailure {
                uiState = uiState.copy(isLoading = false, error = it.message ?: "Erro desconhecido")
            }
        }
    }

    fun clearError() {
        uiState = uiState.copy(error = null)
    }
}

class LoginViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}