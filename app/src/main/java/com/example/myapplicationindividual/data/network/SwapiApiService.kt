package com.example.myapplicationindividual.data.network

import com.example.myapplicationindividual.data.dto.SwapiPeopleListResponseDto
import com.example.myapplicationindividual.data.dto.SwapiSinglePersonResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SwapiApiService {
    @GET("people/{id}")
    suspend fun getPerson(@Path("id") id: Int): SwapiSinglePersonResponseDto

    @GET("people")
    suspend fun getPeople(): SwapiPeopleListResponseDto
}
