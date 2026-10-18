package com.example.myapplicationindividual.data.repository

import com.example.myapplicationindividual.data.mapper.ChuckNorrisMapper
import com.example.myapplicationindividual.data.network.ChuckNorrisApiService
import com.example.myapplicationindividual.data.network.RetrofitClient
import com.example.myapplicationindividual.domain.model.ChuckNorrisFact

class ChuckNorrisRepository(
    private val apiService: ChuckNorrisApiService = RetrofitClient.chuckNorrisApi
) {
    suspend fun getRandomFact(): Result<ChuckNorrisFact> {
        return runCatching {
            val dto = apiService.getRandomJoke()
            ChuckNorrisMapper.toDomain(dto)
        }
    }
}
