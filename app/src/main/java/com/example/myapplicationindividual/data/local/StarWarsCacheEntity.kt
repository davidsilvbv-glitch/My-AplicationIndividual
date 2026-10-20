package com.example.myapplicationindividual.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starwars_cache")
data class StarWarsCacheEntity(
    @PrimaryKey val id: String,
    val name: String,
    val height: String,
    val mass: String,
    val gender: String,
    val birthYear: String,
    val hairColor: String,
    val eyeColor: String,
    val skinColor: String,
    val imageUrl: String
)
