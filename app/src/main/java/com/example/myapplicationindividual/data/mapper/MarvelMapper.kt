package com.example.myapplicationindividual.data.mapper

import com.example.myapplicationindividual.data.dto.MarvelCharacterDto
import com.example.myapplicationindividual.domain.model.MarvelCharacter

object MarvelMapper {
    fun toDomain(dto: MarvelCharacterDto): MarvelCharacter {
        val path = dto.thumbnail?.path?.replace("http://", "https://").orEmpty()
        val ext = dto.thumbnail?.extension.orEmpty()
        val imageUrl = if (path.isNotEmpty() && ext.isNotEmpty()) {
            "$path.$ext"
        } else {
            ""
        }

        val descriptionText = if (dto.description.isNullOrEmpty()) {
            "Sin descripción disponible."
        } else {
            dto.description
        }

        return MarvelCharacter(
            id = dto.id ?: 0,
            name = dto.name ?: "Personaje Marvel",
            description = descriptionText,
            imageUrl = imageUrl
        )
    }
}
