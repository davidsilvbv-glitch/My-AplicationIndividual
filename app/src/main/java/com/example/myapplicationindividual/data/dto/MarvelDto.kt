package com.example.myapplicationindividual.data.dto

import com.google.gson.annotations.SerializedName

data class MarvelCharacterResponseDto(
    @SerializedName("code") val code: Int? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("data") val data: MarvelDataDto? = null
)

data class MarvelDataDto(
    @SerializedName("results") val results: List<MarvelCharacterDto>? = null
)

data class MarvelCharacterDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("thumbnail") val thumbnail: MarvelThumbnailDto? = null
)

data class MarvelThumbnailDto(
    @SerializedName("path") val path: String? = null,
    @SerializedName("extension") val extension: String? = null
)
