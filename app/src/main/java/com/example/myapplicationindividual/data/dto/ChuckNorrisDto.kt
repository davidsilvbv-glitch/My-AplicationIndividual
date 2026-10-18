package com.example.myapplicationindividual.data.dto

import com.google.gson.annotations.SerializedName

data class ChuckNorrisDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("value") val value: String? = null,
    @SerializedName("icon_url") val iconUrl: String? = null,
    @SerializedName("url") val url: String? = null
)
