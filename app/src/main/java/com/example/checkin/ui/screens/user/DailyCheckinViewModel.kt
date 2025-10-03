package com.example.checkin.ui.screens.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.checkin.data.remote.dto.DailyCheckinRequest
import com.example.checkin.data.remote.dto.RespostasCheckinDto
import com.example.checkin.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

data class DailyCheckinUiState(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val error: String? = null
)

class DailyCheckinViewModel(private val repository: UserRepository) : ViewModel() {

    // Estados dos campos do formulário (usamos Float para os Sliders)
    var humor by mutableStateOf(3f)
    var sonoHoras by mutableStateOf(8f)
    var estresse by mutableStateOf(3f)
    var apetite by mutableStateOf(3f)
    var concentracao by mutableStateOf(3f)
    var fadiga by mutableStateOf(3f)
    var observacoes by mutableStateOf("")

    private val _uiState = MutableStateFlow(DailyCheckinUiState())
    val uiState: StateFlow<DailyCheckinUiState> = _uiState.asStateFlow()

    fun submitCheckin() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val respostas = RespostasCheckinDto(
                humor = humor.roundToInt(),
                sonoHoras = sonoHoras,
                estresse = estresse.roundToInt(),
                apetite = apetite.roundToInt(),
                concentracao = concentracao.roundToInt(),
                fadiga = fadiga.roundToInt()
            )
            val request = DailyCheckinRequest(respostas, observacoes)
            val result = repository.submitCheckin(request)

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
class DailyCheckinViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyCheckinViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DailyCheckinViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}