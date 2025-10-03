package com.example.checkin.ui.screens.registerCompany

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.checkin.data.remote.dto.RegisterCompanyRequest
import com.example.checkin.data.remote.dto.RegisterCompanyResponse
import com.example.checkin.data.repository.CompanyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegisterCompanyUiState(
    val isLoading: Boolean = false,
    val successResponse: RegisterCompanyResponse? = null,
    val error: String? = null
)

class RegisterCompanyViewModel(private val repository: CompanyRepository) : ViewModel() {

    var companyName by mutableStateOf("")
    var adminUsername by mutableStateOf("")
    var adminPassword by mutableStateOf("")
    var setores by mutableStateOf("")

    private val _uiState = MutableStateFlow(RegisterCompanyUiState())
    val uiState: StateFlow<RegisterCompanyUiState> = _uiState.asStateFlow()

    fun onCompanyNameChange(name: String) { companyName = name }
    fun onAdminUsernameChange(username: String) { adminUsername = username }
    fun onAdminPasswordChange(password: String) { adminPassword = password }
    fun onSetoresChange(s: String) { setores = s }

    fun registerCompany() {
        if (companyName.isBlank() || adminUsername.isBlank() || adminPassword.isBlank() || setores.isBlank()) {
            _uiState.update { it.copy(error = "Todos os campos são obrigatórios.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val setoresList = setores.split(',').map { it.trim() }.filter { it.isNotEmpty() }

            val request = RegisterCompanyRequest(
                name = companyName,
                adminUsername = adminUsername,
                adminPassword = adminPassword,
                setores = setoresList
            )

            val result = repository.registerCompany(request)

            result.onSuccess { response ->
                _uiState.update { it.copy(isLoading = false, successResponse = response) }
            }.onFailure { exception ->
                _uiState.update { it.copy(isLoading = false, error = exception.message) }
            }
        }
    }

    fun clearMessages() {
        _uiState.update { it.copy(error = null, successResponse = null) }
    }
}

class RegisterCompanyViewModelFactory(private val repository: CompanyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterCompanyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterCompanyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}