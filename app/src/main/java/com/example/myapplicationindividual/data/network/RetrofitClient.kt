package com.example.myapplicationindividual.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val chuckNorrisApi: ChuckNorrisApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChuckNorrisApiService::class.java)
    }

    val swapiApi: SwapiApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.swapi.tech/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SwapiApiService::class.java)
    }

    val marvelApi: MarvelApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApiService::class.java)
    }

    val sqliteAiApi: SQLiteAiApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.sqlite.ai/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SQLiteAiApiService::class.java)
    }
}
