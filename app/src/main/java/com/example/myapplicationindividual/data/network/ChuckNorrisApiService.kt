package com.example.myapplicationindividual.data.network

import com.example.myapplicationindividual.data.dto.ChuckNorrisDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApiService {
    @GET("jokes/random")
    suspend fun getRandomJoke(
        @Query("category") category: String? = null
    ): ChuckNorrisDto

    @GET("jokes/categories")
    suspend fun getCategories(): List<String>
}
