package com.example.myapplicationindividual.data.mapper

import com.example.myapplicationindividual.data.dto.ChuckNorrisDto
import com.example.myapplicationindividual.domain.model.ChuckNorrisFact

object ChuckNorrisMapper {
    fun toDomain(dto: ChuckNorrisDto): ChuckNorrisFact {
        return ChuckNorrisFact(
            id = dto.id.orEmpty(),
            factText = dto.value ?: "Sin información sobre Chuck Norris.",
            iconUrl = dto.iconUrl.orEmpty(),
            url = dto.url.orEmpty()
        )
    }
}
