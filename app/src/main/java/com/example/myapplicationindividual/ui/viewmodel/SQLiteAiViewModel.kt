package com.example.myapplicationindividual.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationindividual.data.network.RetrofitClient
import com.example.myapplicationindividual.data.repository.SQLiteAiRepository
import com.example.myapplicationindividual.domain.model.CloudDatabaseItem
import com.example.myapplicationindividual.utils.NotificationHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SQLiteAiViewModel(
    application: Application,
    private val repository: SQLiteAiRepository = SQLiteAiRepository()
) : AndroidViewModel(application) {

    private val _items = MutableStateFlow<List<CloudDatabaseItem>>(emptyList())
    val items: StateFlow<List<CloudDatabaseItem>> = _items.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _apiKey = MutableStateFlow(RetrofitClient.DEFAULT_SQLITE_CLOUD_API_KEY)
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
                    NotificationHelper.showNotification(
                        getApplication(),
                        "Sincronización Exitosa",
                        "Se han sincronizado ${cloudItems.size} elementos desde SQLite Cloud."
                    )
                }
                .onFailure { exc ->
                    _isLoading.value = false
                    _error.value = exc.localizedMessage ?: "Error conectando con SQLite.ai Cloud"
                    NotificationHelper.showNotification(
                        getApplication(),
                        "Error de Sincronización",
                        "No se pudo conectar con SQLite Cloud."
                    )
                }
        }
    }
}
