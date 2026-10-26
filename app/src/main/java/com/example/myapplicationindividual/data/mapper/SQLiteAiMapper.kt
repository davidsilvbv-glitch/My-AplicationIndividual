package com.example.myapplicationindividual.data.mapper

import com.example.myapplicationindividual.data.dto.SQLiteAiRowDto
import com.example.myapplicationindividual.domain.model.CloudDatabaseItem

object SQLiteAiMapper {
    fun toDomain(dto: SQLiteAiRowDto): CloudDatabaseItem {
        return CloudDatabaseItem(
            id = dto.id ?: "",
            title = dto.title ?: "Registro Sin Título",
            description = dto.description ?: "Sin descripción.",
            createdAt = dto.createdAt ?: "Fecha N/A"
        )
    }
}
