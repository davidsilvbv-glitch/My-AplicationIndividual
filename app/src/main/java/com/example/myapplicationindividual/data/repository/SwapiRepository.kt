package com.example.myapplicationindividual.data.repository

import com.example.myapplicationindividual.data.local.StarWarsCacheDao
import com.example.myapplicationindividual.data.local.StarWarsCacheEntity
import com.example.myapplicationindividual.data.mapper.SwapiMapper
import com.example.myapplicationindividual.data.network.RetrofitClient
import com.example.myapplicationindividual.data.network.SwapiApiService
import com.example.myapplicationindividual.domain.model.StarWarsCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class SwapiRepository(
    private val cacheDao: StarWarsCacheDao? = null,
    private val apiService: SwapiApiService = RetrofitClient.swapiApi
) {
    fun observeCachedCharacters(): Flow<List<StarWarsCharacter>> {
        return if (cacheDao != null) {
            cacheDao.observeAll().map { entities ->
                entities.map {
                    StarWarsCharacter(
                        id = it.id,
                        name = it.name,
                        height = it.height,
                        mass = it.mass,
                        gender = it.gender,
                        birthYear = it.birthYear,
                        hairColor = it.hairColor,
                        eyeColor = it.eyeColor,
                        skinColor = it.skinColor,
                        imageUrl = it.imageUrl
                    )
                }
            }
        } else {
            flowOf(emptyList())
        }
    }

    suspend fun hasCachedCharacters(): Boolean {
        return (cacheDao?.count() ?: 0) > 0
    }

    suspend fun getCharacter(id: Int): Result<StarWarsCharacter> {
        return runCatching {
            val response = apiService.getPerson(id)
            val resultDto = response.result ?: throw Exception("No se encontró el personaje de Star Wars.")
            val character = SwapiMapper.toDomain(resultDto)

            cacheDao?.insertAll(
                listOf(
                    StarWarsCacheEntity(
                        id = character.id,
                        name = character.name,
                        height = character.height,
                        mass = character.mass,
                        gender = character.gender,
                        birthYear = character.birthYear,
                        hairColor = character.hairColor,
                        eyeColor = character.eyeColor,
                        skinColor = character.skinColor,
                        imageUrl = character.imageUrl
                    )
                )
            )

            character
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
