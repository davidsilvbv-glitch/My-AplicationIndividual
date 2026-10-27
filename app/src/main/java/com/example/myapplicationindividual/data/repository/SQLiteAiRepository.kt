package com.example.myapplicationindividual.data.repository

import com.example.myapplicationindividual.data.mapper.SQLiteAiMapper
import com.example.myapplicationindividual.data.network.RetrofitClient
import com.example.myapplicationindividual.data.network.SQLiteAiApiService
import com.example.myapplicationindividual.domain.model.CloudDatabaseItem

class SQLiteAiRepository(
    private val apiService: SQLiteAiApiService = RetrofitClient.sqliteAiApi
) {
    suspend fun getCloudItems(apiKey: String): Result<List<CloudDatabaseItem>> {
        return runCatching {
            val tokenHeader = if (apiKey.startsWith("Bearer ")) apiKey else "Bearer $apiKey"
            // Consultando la tabla 'albums' de la base de datos Chinook en SQLite Cloud que ves en la web
            val response = apiService.getQueryResults(tokenHeader, "SELECT * FROM albums;")
            val dtoList = response.data ?: emptyList()
            dtoList.map { SQLiteAiMapper.toDomain(it) }
        }
    }

    fun getSampleCloudItems(): List<CloudDatabaseItem> {
        return listOf(
            CloudDatabaseItem(
                id = "1",
                title = "For Those About To Rock We Salute You",
                description = "Artista ID: 1 (Chinook SQLite Cloud)",
                createdAt = "Demostración"
            ),
            CloudDatabaseItem(
                id = "2",
                title = "Balls to the Wall",
                description = "Artista ID: 2 (Chinook SQLite Cloud)",
                createdAt = "Demostración"
            )
        )
    }
}
