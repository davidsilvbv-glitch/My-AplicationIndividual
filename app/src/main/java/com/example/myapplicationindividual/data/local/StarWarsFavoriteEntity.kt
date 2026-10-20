package com.example.myapplicationindividual.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starwars_favorites")
data class StarWarsFavoriteEntity(
    @PrimaryKey val id: String,
    val name: String,
    val height: String,
    val mass: String,
    val gender: String,
    val birthYear: String,
    val imageUrl: String,
    val addedAt: Long = System.currentTimeMillis()
)
