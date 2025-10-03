package com.example.checkin.ui.screens.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.checkin.data.remote.dto.QuestionnaireAnswersRequest
import com.example.checkin.data.remote.dto.RespostasQuestionarioDto
import com.example.checkin.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

data class WeeklyQuestionnaireUiState(
    val isSubmitting: Boolean = false,
    val error: String? = null,
    val submissionSuccessMessage: String? = null
)

class WeeklyQuestionnaireViewModel(private val repository: UserRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(WeeklyQuestionnaireUiState())
    val uiState: StateFlow<WeeklyQuestionnaireUiState> = _uiState.asStateFlow()


    var relacionamentos by mutableStateOf(5f)
    var apoioGestor by mutableStateOf(5f)
    var cargaTrabalho by mutableStateOf(5f)
    var clarezaFuncao by mutableStateOf(5f)
    var segurancaPercebida by mutableStateOf(5f)

    fun submitAnswers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSubmitting = true, error = null) }

            val respostasDto = RespostasQuestionarioDto(
                relacionamentos = relacionamentos.roundToInt(),
                apoioGestor = apoioGestor.roundToInt(),
                cargaTrabalho = cargaTrabalho.roundToInt(),
                clarezaFuncao = clarezaFuncao.roundToInt(),
                segurancaPercebida = segurancaPercebida.roundToInt()
            )

            val request = QuestionnaireAnswersRequest(respostasDto)
            val result = repository.submitQuestionnaire(request)

            result.onSuccess { message ->
                _uiState.update { it.copy(isSubmitting = false, submissionSuccessMessage = message) }
            }.onFailure { exception ->
                _uiState.update { it.copy(isSubmitting = false, error = exception.message) }
            }
        }
    }

    fun clearMessages() {
        _uiState.update { it.copy(error = null, submissionSuccessMessage = null) }
    }
}

class WeeklyQuestionnaireViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeeklyQuestionnaireViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeeklyQuestionnaireViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}