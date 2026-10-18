package com.example.myapplicationindividual.data.mapper

import com.example.myapplicationindividual.data.dto.SwapiPersonResultDto
import com.example.myapplicationindividual.data.dto.SwapiPersonSummaryDto
import com.example.myapplicationindividual.domain.model.StarWarsCharacter

object SwapiMapper {
    fun toDomain(dto: SwapiPersonResultDto): StarWarsCharacter {
        val props = dto.properties
        return StarWarsCharacter(
            id = dto.uid ?: dto.id.orEmpty(),
            name = props?.name ?: "Personaje Desconocido",
            height = props?.height ?: "N/A",
            mass = props?.mass ?: "N/A",
            gender = props?.gender ?: "N/A",
            birthYear = props?.birthYear ?: "N/A"
        )
    }

    fun toDomainFromSummary(dto: SwapiPersonSummaryDto): StarWarsCharacter {
        return StarWarsCharacter(
            id = dto.uid.orEmpty(),
            name = dto.name ?: "Personaje Desconocido",
            height = "N/A",
            mass = "N/A",
            gender = "N/A",
            birthYear = "N/A"
        )
    }
}
