package com.example.myapplicationindividual.data.mapper

import com.example.myapplicationindividual.data.dto.SwapiPersonResultDto
import com.example.myapplicationindividual.data.dto.SwapiPersonSummaryDto
import com.example.myapplicationindividual.domain.model.StarWarsCharacter

object SwapiMapper {
    fun toDomain(dto: SwapiPersonResultDto): StarWarsCharacter {
        val props = dto.properties
        val characterId = dto.uid ?: dto.id.orEmpty()
        val imgUrl = if (characterId.isNotEmpty()) {
            "https://starwars-visualguide.com/assets/img/characters/$characterId.jpg"
        } else {
            ""
        }

        return StarWarsCharacter(
            id = characterId,
            name = props?.name ?: "Personaje Desconocido",
            height = props?.height ?: "N/A",
            mass = props?.mass ?: "N/A",
            gender = props?.gender ?: "N/A",
            birthYear = props?.birthYear ?: "N/A",
            hairColor = props?.hairColor ?: "N/A",
            eyeColor = props?.eyeColor ?: "N/A",
            skinColor = props?.skinColor ?: "N/A",
            imageUrl = imgUrl
        )
    }

    fun toDomainFromSummary(dto: SwapiPersonSummaryDto): StarWarsCharacter {
        val characterId = dto.uid.orEmpty()
        val imgUrl = if (characterId.isNotEmpty()) {
            "https://starwars-visualguide.com/assets/img/characters/$characterId.jpg"
        } else {
            ""
        }

        return StarWarsCharacter(
            id = characterId,
            name = dto.name ?: "Personaje Desconocido",
            height = "N/A",
            mass = "N/A",
            gender = "N/A",
            birthYear = "N/A",
            hairColor = "N/A",
            eyeColor = "N/A",
            skinColor = "N/A",
            imageUrl = imgUrl
        )
    }
}
