package com.example.myapplicationindividual.data.network

import com.example.myapplicationindividual.data.dto.SQLiteAiQueryResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SQLiteAiApiService {

    @GET("v1/webrequest")
    suspend fun getQueryResults(
        @Header("Authorization") token: String,
        @Query("sql") sqlQuery: String = "SELECT * FROM chinook.sqlite;"
    ): SQLiteAiQueryResponseDto
}
