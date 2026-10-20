package com.example.myapplicationindividual.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationindividual.data.local.AppDatabase
import com.example.myapplicationindividual.data.repository.StarWarsFavoriteRepository
import com.example.myapplicationindividual.data.repository.SwapiRepository
import com.example.myapplicationindividual.domain.model.StarWarsCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class SwapiUiState(
    val isLoading: Boolean = false,
    val character: StarWarsCharacter? = null,
    val currentId: Int = 1,
    val isFavorite: Boolean = false,
    val favoriteIds: List<String> = emptyList(),
    val errorMessage: String? = null
)

class SwapiViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val repository = SwapiRepository(db.starWarsCacheDao())
    private val favoriteRepository = StarWarsFavoriteRepository(db.starWarsFavoriteDao())

    private val _characterState = MutableStateFlow(SwapiUiState())

    val uiState: StateFlow<SwapiUiState> = combine(
        _characterState,
        favoriteRepository.observeFavoriteIds()
    ) { state, favIds ->
        val isFav = state.character?.id in favIds
        state.copy(
            isFavorite = isFav,
            favoriteIds = favIds
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SwapiUiState()
    )

    init {
        loadCharacter(1)
    }

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            _characterState.value = _characterState.value.copy(isLoading = true, errorMessage = null, currentId = id)
            val result = repository.getCharacter(id)
            result.onSuccess { character ->
                _characterState.value = _characterState.value.copy(
                    isLoading = false,
                    character = character,
                    errorMessage = null
                )
            }.onFailure { error ->
                _characterState.value = _characterState.value.copy(
                    isLoading = false,
                    errorMessage = error.localizedMessage ?: "No se pudo obtener información del personaje $id."
                )
            }
        }
    }

    fun toggleFavorite() {
        val currentCharacter = uiState.value.character ?: return
        val isFav = uiState.value.isFavorite
        viewModelScope.launch {
            favoriteRepository.toggleFavorite(currentCharacter, isFav)
        }
    }

    fun loadNextCharacter() {
        val nextId = _characterState.value.currentId + 1
        loadCharacter(nextId)
    }

    fun loadPreviousCharacter() {
        val current = _characterState.value.currentId
        if (current > 1) {
            loadCharacter(current - 1)
        }
    }
}
