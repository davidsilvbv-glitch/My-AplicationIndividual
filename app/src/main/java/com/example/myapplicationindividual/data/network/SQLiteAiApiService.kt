package com.example.myapplicationindividual.data.network

import com.example.myapplicationindividual.data.dto.SQLiteAiQueryResponseDto
import com.example.myapplicationindividual.data.dto.SQLiteAiRequestDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SQLiteAiApiService {

    @POST("v2/weblite/sql")
    suspend fun executeQuery(
        @Header("Authorization") token: String,
        @Body body: SQLiteAiRequestDto
    ): SQLiteAiQueryResponseDto
}
