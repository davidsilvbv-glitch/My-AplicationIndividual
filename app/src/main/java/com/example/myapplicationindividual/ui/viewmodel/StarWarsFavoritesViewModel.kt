package com.example.myapplicationindividual.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationindividual.data.local.AppDatabase
import com.example.myapplicationindividual.data.repository.StarWarsFavoriteRepository
import com.example.myapplicationindividual.domain.model.StarWarsCharacter
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class StarWarsFavoritesUiState(
    val favorites: List<StarWarsCharacter> = emptyList(),
    val isLoading: Boolean = false
)

class StarWarsFavoritesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val favoriteRepository = StarWarsFavoriteRepository(
        AppDatabase.getInstance(application).starWarsFavoriteDao()
    )

    val uiState: StateFlow<StarWarsFavoritesUiState> = favoriteRepository.observeFavorites()
        .map { list -> StarWarsFavoritesUiState(favorites = list, isLoading = false) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = StarWarsFavoritesUiState(isLoading = true)
        )

    fun removeFavorite(id: String) {
        viewModelScope.launch {
            favoriteRepository.removeFavorite(id)
        }
    }
}
