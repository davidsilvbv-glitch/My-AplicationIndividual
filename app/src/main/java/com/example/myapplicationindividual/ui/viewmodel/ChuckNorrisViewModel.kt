package com.example.myapplicationindividual.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationindividual.data.repository.ChuckNorrisRepository
import com.example.myapplicationindividual.domain.model.ChuckNorrisFact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ChuckNorrisUiState(
    val isLoading: Boolean = false,
    val fact: ChuckNorrisFact? = null,
    val errorMessage: String? = null
)

class ChuckNorrisViewModel(
    private val repository: ChuckNorrisRepository = ChuckNorrisRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChuckNorrisUiState())
    val uiState: StateFlow<ChuckNorrisUiState> = _uiState.asStateFlow()

    init {
        loadRandomFact()
    }

    fun loadRandomFact() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            val result = repository.getRandomFact()
            result.onSuccess { fact ->
                _uiState.value = ChuckNorrisUiState(isLoading = false, fact = fact)
            }.onFailure { error ->
                _uiState.value = ChuckNorrisUiState(
                    isLoading = false,
                    errorMessage = error.localizedMessage ?: "Error al cargar dato de Chuck Norris."
                )
            }
        }
    }
}
