package com.example.checkin.ui.screens.registerCompanyAdmin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.checkin.data.remote.dto.RegisterAdminRequest
import com.example.checkin.data.repository.CompanyAdminRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CreateAdminUiState(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val error: String? = null
)

class CreateCompanyAdminViewModel(private val repository: CompanyAdminRepository) : ViewModel() {

    var username by mutableStateOf("")
    var password by mutableStateOf("")

    private val _uiState = MutableStateFlow(CreateAdminUiState())
    val uiState: StateFlow<CreateAdminUiState> = _uiState.asStateFlow()

    fun onUsernameChange(u: String) { username = u }
    fun onPasswordChange(p: String) { password = p }

    fun createAdmin() {
        if (username.isBlank() || password.isBlank()) {
            _uiState.update { it.copy(error = "Preencha usuário e senha.") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val request = RegisterAdminRequest(username, password)
            val result = repository.createCompanyAdmin(request)

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
class CreateCompanyAdminViewModelFactory(private val repository: CompanyAdminRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateCompanyAdminViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateCompanyAdminViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}