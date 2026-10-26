package com.example.myapplicationindividual.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationindividual.data.repository.SQLiteAiRepository
import com.example.myapplicationindividual.domain.model.CloudDatabaseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SQLiteAiViewModel(
    private val repository: SQLiteAiRepository = SQLiteAiRepository()
) : ViewModel() {

    private val _items = MutableStateFlow<List<CloudDatabaseItem>>(kotlin.collections.emptyList())
    val items: StateFlow<List<CloudDatabaseItem>> = _items.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _apiKey = MutableStateFlow("")
    val apiKey: StateFlow<String> = _apiKey.asStateFlow()

    init {
        _items.value = repository.getSampleCloudItems()
    }

    fun onApiKeyChanged(key: String) {
        _apiKey.value = key
    }

    fun fetchCloudData() {
        if (_apiKey.value.isBlank()) {
            _items.value = repository.getSampleCloudItems()
            _error.value = "Mostrando datos de demostración. Ingresa tu API Key para conectar con tu cuenta."
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.getCloudItems(_apiKey.value)
                .onSuccess { cloudItems ->
                    _items.value = cloudItems
                    _isLoading.value = false
                }
                .onFailure { exc ->
                    _isLoading.value = false
                    _error.value = exc.localizedMessage ?: "Error conectando con SQLite.ai Cloud"
                }
        }
    }
}
