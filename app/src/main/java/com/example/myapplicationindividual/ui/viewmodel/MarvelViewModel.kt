package com.example.myapplicationindividual.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationindividual.data.repository.MarvelRepository
import com.example.myapplicationindividual.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MarvelUiState(
    val isLoading: Boolean = false,
    val characters: List<MarvelCharacter> = emptyList(),
    val isUsingSampleData: Boolean = false,
    val errorMessage: String? = null
)

class MarvelViewModel(
    private val repository: MarvelRepository = MarvelRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(MarvelUiState())
    val uiState: StateFlow<MarvelUiState> = _uiState.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters(apiKey: String = "", ts: String = "1", hash: String = "") {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            val result = repository.getCharacters(apiKey, ts, hash)
            result.onSuccess { list ->
                if (list.isNotEmpty()) {
                    _uiState.value = MarvelUiState(
                        isLoading = false,
                        characters = list,
                        isUsingSampleData = false
                    )
                } else {
                    // Fallback to sample data if list is empty or authorization fails
                    loadSampleCharacters("Sin resultados desde la API pública de Marvel.")
                }
            }.onFailure {
                // Fallback to sample data on failure (e.g. missing or invalid Marvel API key)
                loadSampleCharacters("Mostrando catálogo de muestra de Marvel (la API oficial de Marvel requiere clave API configurada).")
            }
        }
    }

    fun loadSampleCharacters(message: String? = null) {
        val sampleList = repository.getSampleMarvelCharacters()
        _uiState.value = MarvelUiState(
            isLoading = false,
            characters = sampleList,
            isUsingSampleData = true,
            errorMessage = message
        )
    }
}
