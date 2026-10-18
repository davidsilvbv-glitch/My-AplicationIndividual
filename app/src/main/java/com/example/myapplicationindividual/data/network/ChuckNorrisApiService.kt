package com.example.myapplicationindividual.data.network

import com.example.myapplicationindividual.data.dto.ChuckNorrisDto
import retrofit2.http.GET

interface ChuckNorrisApiService {
    @GET("jokes/random")
    suspend fun getRandomJoke(): ChuckNorrisDto
}
