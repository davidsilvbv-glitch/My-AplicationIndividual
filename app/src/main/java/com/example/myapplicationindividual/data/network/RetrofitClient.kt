package com.example.myapplicationindividual.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val DEFAULT_SQLITE_CLOUD_API_KEY = "orgkey_e2e34bce68398edb0726aa60ea549bd24da0355a184108ed727bfcedbb222a0a"

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
            .baseUrl("https://cguy666cdk.sqlite.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SQLiteAiApiService::class.java)
    }
}
