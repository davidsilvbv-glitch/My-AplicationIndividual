package com.example.myapplicationindividual.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationindividual.data.repository.SwapiRepository
import com.example.myapplicationindividual.domain.model.StarWarsCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SwapiUiState(
    val isLoading: Boolean = false,
    val character: StarWarsCharacter? = null,
    val currentId: Int = 1,
    val errorMessage: String? = null
)

class SwapiViewModel(
    private val repository: SwapiRepository = SwapiRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(SwapiUiState())
    val uiState: StateFlow<SwapiUiState> = _uiState.asStateFlow()

    init {
        loadCharacter(1)
    }

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null, currentId = id)
            val result = repository.getCharacter(id)
            result.onSuccess { character ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    character = character,
                    errorMessage = null
                )
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = error.localizedMessage ?: "No se pudo obtener información del personaje $id."
                )
            }
        }
    }

    fun loadNextCharacter() {
        val nextId = _uiState.value.currentId + 1
        loadCharacter(nextId)
    }

    fun loadPreviousCharacter() {
        val current = _uiState.value.currentId
        if (current > 1) {
            loadCharacter(current - 1)
        }
    }
}
