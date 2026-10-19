package com.example.myapplicationindividual.data.repository

import com.example.myapplicationindividual.data.mapper.ChuckNorrisMapper
import com.example.myapplicationindividual.data.network.ChuckNorrisApiService
import com.example.myapplicationindividual.data.network.RetrofitClient
import com.example.myapplicationindividual.domain.model.ChuckNorrisFact

class ChuckNorrisRepository(
    private val apiService: ChuckNorrisApiService = RetrofitClient.chuckNorrisApi
) {
    suspend fun getRandomFact(category: String? = null): Result<ChuckNorrisFact> {
        return runCatching {
            val dto = apiService.getRandomJoke(category)
            ChuckNorrisMapper.toDomain(dto)
        }
    }

    suspend fun getCategories(): Result<List<String>> {
        return runCatching {
            apiService.getCategories()
        }
    }
}
