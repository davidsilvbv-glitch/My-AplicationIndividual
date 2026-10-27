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
            // Consultando la tabla de favoritos/personajes de Star Wars y Marvel en la nube
            val response = apiService.getQueryResults(tokenHeader, "SELECT * FROM favorites;")
            val dtoList = response.data ?: emptyList()
            dtoList.map { SQLiteAiMapper.toDomain(it) }
        }
    }

    fun getSampleCloudItems(): List<CloudDatabaseItem> {
        return listOf(
            CloudDatabaseItem(
                id = "sw_1",
                title = "Luke Skywalker (Star Wars)",
                description = "Favorito guardado en SQLite Cloud",
                createdAt = "Demostración"
            ),
            CloudDatabaseItem(
                id = "marvel_1",
                title = "Spider-Man (Marvel)",
                description = "Personaje sincronizado en la nube",
                createdAt = "Demostración"
            ),
            CloudDatabaseItem(
                id = "cn_1",
                title = "Chuck Norris Fact #1",
                description = "Chiste guardado en la nube",
                createdAt = "Demostración"
            )
        )
    }
}
