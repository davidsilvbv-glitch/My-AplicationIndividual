package com.example.myapplicationindividual.data.dto

import com.google.gson.annotations.SerializedName

data class SwapiSinglePersonResponseDto(
    @SerializedName("message") val message: String? = null,
    @SerializedName("result") val result: SwapiPersonResultDto? = null
)

data class SwapiPersonResultDto(
    @SerializedName("properties") val properties: SwapiPropertiesDto? = null,
    @SerializedName("uid") val uid: String? = null,
    @SerializedName("_id") val id: String? = null
)

data class SwapiPropertiesDto(
    @SerializedName("name") val name: String? = null,
    @SerializedName("height") val height: String? = null,
    @SerializedName("mass") val mass: String? = null,
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("birth_year") val birthYear: String? = null,
    @SerializedName("hair_color") val hairColor: String? = null,
    @SerializedName("eye_color") val eyeColor: String? = null,
    @SerializedName("skin_color") val skinColor: String? = null
)

data class SwapiPeopleListResponseDto(
    @SerializedName("message") val message: String? = null,
    @SerializedName("results") val results: List<SwapiPersonSummaryDto>? = null
)

data class SwapiPersonSummaryDto(
    @SerializedName("uid") val uid: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("url") val url: String? = null
)
