package com.example.myapplicationindividual.data.dto

import com.google.gson.annotations.SerializedName

data class SQLiteAiQueryResponseDto(
    @SerializedName("status") val status: String? = null,
    @SerializedName("data") val data: List<SQLiteAiRowDto>? = null,
    @SerializedName("message") val message: String? = null
)

data class SQLiteAiRowDto(
    @SerializedName("AlbumId") val albumId: Int? = null,
    @SerializedName("Title") val title: String? = null,
    @SerializedName("ArtistId") val artistId: Int? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("created_at") val createdAt: String? = null
)
