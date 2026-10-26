package com.example.myapplicationindividual.data.network

import com.example.myapplicationindividual.data.dto.SQLiteAiQueryResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface SQLiteAiApiService {

    @GET("api/v1/query")
    suspend fun getQueryResults(
        @Header("Authorization") token: String,
        @Query("sql") sqlQuery: String = "SELECT * FROM items;"
    ): SQLiteAiQueryResponseDto

    @POST("api/v1/execute")
    suspend fun executeStatement(
        @Header("Authorization") token: String,
        @Body body: Map<String, String>
    ): SQLiteAiQueryResponseDto
}
