package com.example.myapplicationindividual.data.repository

import com.example.myapplicationindividual.data.local.StarWarsFavoriteDao
import com.example.myapplicationindividual.data.local.StarWarsFavoriteEntity
import com.example.myapplicationindividual.domain.model.StarWarsCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StarWarsFavoriteRepository(
    private val favoriteDao: StarWarsFavoriteDao
) {
    fun observeFavorites(): Flow<List<StarWarsCharacter>> {
        return favoriteDao.observeFavorites().map { entities ->
            entities.map { entity ->
                StarWarsCharacter(
                    id = entity.id,
                    name = entity.name,
                    height = entity.height,
                    mass = entity.mass,
                    gender = entity.gender,
                    birthYear = entity.birthYear,
                    hairColor = "N/A",
                    eyeColor = "N/A",
                    skinColor = "N/A",
                    imageUrl = entity.imageUrl
                )
            }
        }
    }

    fun observeFavoriteIds(): Flow<List<String>> {
        return favoriteDao.observeFavoriteIds()
    }

    suspend fun toggleFavorite(character: StarWarsCharacter, isCurrentlyFavorite: Boolean) {
        if (isCurrentlyFavorite) {
            favoriteDao.deleteById(character.id)
        } else {
            val entity = StarWarsFavoriteEntity(
                id = character.id,
                name = character.name,
                height = character.height,
                mass = character.mass,
                gender = character.gender,
                birthYear = character.birthYear,
                imageUrl = character.imageUrl
            )
            favoriteDao.insert(entity)
        }
    }

    suspend fun removeFavorite(id: String) {
        favoriteDao.deleteById(id)
    }
}
