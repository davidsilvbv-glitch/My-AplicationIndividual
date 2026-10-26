package com.example.myapplicationindividual.data.repository

import com.example.myapplicationindividual.data.mapper.SQLiteAiMapper
import com.example.myapplicationindividual.data.network.RetrofitClient
import com.example.myapplicationindividual.data.network.SQLiteAiApiService
import com.example.myapplicationindividual.domain.model.CloudDatabaseItem

class SQLiteAiRepository(
    private val apiService: SQLiteAiApiService = RetrofitClient.sqliteAiApi
) {
    suspend fun getCloudItems(apiKey: String): kotlin.Result<List<CloudDatabaseItem>> {
        return kotlin.runCatching {
            val tokenHeader = if (apiKey.startsWith("Bearer ")) apiKey else "Bearer $apiKey"
            val response = apiService.getQueryResults(tokenHeader, "SELECT * FROM items;")
            val dtoList = response.data ?: kotlin.collections.emptyList()
            dtoList.map { SQLiteAiMapper.toDomain(it) }
        }
    }

    fun getSampleCloudItems(): List<CloudDatabaseItem> {
        return kotlin.collections.listOf(
            CloudDatabaseItem(
                id = "sqlite_cloud_1",
                title = "Tabla Principal - SQLite Cloud",
                description = "Conexión a la base de datos distribuida en la nube con SQLite.ai",
                createdAt = "2026-10-26"
            ),
            CloudDatabaseItem(
                id = "sqlite_cloud_2",
                title = "Sincronización en Tiempo Real",
                description = "Datos persistidos en el servidor remoto de SQLite Cloud",
                createdAt = "2026-10-26"
            )
        )
    }
}
