package com.example.checkin.ui.screens.resetPassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.checkin.data.repository.PlatformAdminRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ResetPasswordUiState(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val error: String? = null
)

class ResetPasswordViewModel(private val repository: PlatformAdminRepository) : ViewModel() {

    var userEmail by mutableStateOf("")
    var newPassword by mutableStateOf("")

    private val _uiState = MutableStateFlow(ResetPasswordUiState())
    val uiState: StateFlow<ResetPasswordUiState> = _uiState.asStateFlow()

    fun onUserEmailChange(email: String) { userEmail = email }
    fun onNewPasswordChange(password: String) { newPassword = password }

    fun resetPassword() {
        if (userEmail.isBlank() || newPassword.isBlank()) {
            _uiState.update { it.copy(error = "Preencha o email do usuário e a nova senha.") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = repository.resetPassword(userEmail, newPassword)

            result.onSuccess { message ->
                _uiState.update { it.copy(isLoading = false, successMessage = message) }
            }.onFailure { exception ->
                _uiState.update { it.copy(isLoading = false, error = exception.message) }
            }
        }
    }

    fun clearMessages() {
        _uiState.update { it.copy(error = null, successMessage = null) }
    }
}

// Factory
class ResetPasswordViewModelFactory(private val repository: PlatformAdminRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResetPasswordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResetPasswordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}