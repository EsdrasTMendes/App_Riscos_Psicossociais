package com.example.checkin.ui.screens.userRegistration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.checkin.data.remote.dto.RegisterAdminRequest
import com.example.checkin.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UserRegistrationUiState(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val error: String? = null
)

class UserRegistrationViewModel(private val repository: AuthRepository) : ViewModel() {

    var companyId by mutableStateOf("")
    var username by mutableStateOf("")
    var password by mutableStateOf("")

    private val _uiState = MutableStateFlow(UserRegistrationUiState())
    val uiState: StateFlow<UserRegistrationUiState> = _uiState.asStateFlow()

    fun onCompanyIdChange(id: String) { companyId = id }
    fun onUsernameChange(u: String) { username = u }
    fun onPasswordChange(p: String) { password = p }

    fun registerUser() {
        if (companyId.isBlank() || username.isBlank() || password.isBlank()) {
            _uiState.update { it.copy(error = "Todos os campos são obrigatórios.") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val request = RegisterAdminRequest(username, password)
            val result = repository.registerUser(companyId, request)

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

class UserRegistrationViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserRegistrationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserRegistrationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}