package com.example.myapplicationindividual.data.repository

import com.example.myapplicationindividual.data.mapper.MarvelMapper
import com.example.myapplicationindividual.data.network.MarvelApiService
import com.example.myapplicationindividual.data.network.RetrofitClient
import com.example.myapplicationindividual.domain.model.MarvelCharacter

class MarvelRepository(
    private val apiService: MarvelApiService = RetrofitClient.marvelApi
) {
    suspend fun getCharacters(
        apiKey: String = "public_key_placeholder",
        ts: String = "1",
        hash: String = "hash_placeholder"
    ): Result<List<MarvelCharacter>> {
        return runCatching {
            val response = apiService.getCharacters(apiKey, ts, hash)
            val dtoList = response.data?.results ?: emptyList()
            dtoList.map { MarvelMapper.toDomain(it) }
        }
    }

    fun getSampleMarvelCharacters(): List<MarvelCharacter> {
        return listOf(
            MarvelCharacter(
                id = 1009351,
                name = "Spider-Man",
                description = "Bitten by a radioactive spider, Peter Parker's arachnid abilities give him amazing powers.",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/3/50/5261a800401fe.jpg"
            ),
            MarvelCharacter(
                id = 1009368,
                name = "Iron Man",
                description = "Wounded, captured and forced to build a weapon by his enemies, billionaire industrialist Tony Stark instead created an advanced suit of armor.",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55.jpg"
            ),
            MarvelCharacter(
                id = 1009610,
                name = "Captain America",
                description = "Vowing to serve his country any way he could, young Steve Rogers took the super-soldier serum to become America's one-man army.",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/3/50/537ba56d31087.jpg"
            ),
            MarvelCharacter(
                id = 1009664,
                name = "Thor",
                description = "As the Norse God of Thunder and Odin's son, Thor uses his immense strength and hammer Mjolnir to protect Earth and Asgard.",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350.jpg"
            )
        )
    }
}
