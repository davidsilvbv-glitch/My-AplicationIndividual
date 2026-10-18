package com.example.myapplicationindividual.data.repository

import com.example.myapplicationindividual.data.mapper.SwapiMapper
import com.example.myapplicationindividual.data.network.RetrofitClient
import com.example.myapplicationindividual.data.network.SwapiApiService
import com.example.myapplicationindividual.domain.model.StarWarsCharacter

class SwapiRepository(
    private val apiService: SwapiApiService = RetrofitClient.swapiApi
) {
    suspend fun getCharacter(id: Int): Result<StarWarsCharacter> {
        return runCatching {
            val response = apiService.getPerson(id)
            val resultDto = response.result ?: throw Exception("No se encontró el personaje de Star Wars.")
            SwapiMapper.toDomain(resultDto)
        }
    }

    suspend fun getPeopleList(): Result<List<StarWarsCharacter>> {
        return runCatching {
            val response = apiService.getPeople()
            val results = response.results ?: emptyList()
            results.map { SwapiMapper.toDomainFromSummary(it) }
        }
    }
}
