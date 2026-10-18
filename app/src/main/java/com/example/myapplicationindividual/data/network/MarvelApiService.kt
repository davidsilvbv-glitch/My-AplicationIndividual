package com.example.myapplicationindividual.data.network

import com.example.myapplicationindividual.data.dto.MarvelCharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int = 20
    ): MarvelCharacterResponseDto
}
