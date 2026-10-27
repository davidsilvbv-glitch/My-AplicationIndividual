package com.example.myapplicationindividual.data.mapper

import com.example.myapplicationindividual.data.dto.SQLiteAiRowDto
import com.example.myapplicationindividual.domain.model.CloudDatabaseItem

object SQLiteAiMapper {
    fun toDomain(dto: SQLiteAiRowDto): CloudDatabaseItem {
        return CloudDatabaseItem(
            id = dto.id ?: "0",
            title = dto.name ?: dto.title ?: "Personaje / Item de la App",
            description = dto.description ?: "Sincronizado desde SQLite Cloud",
            createdAt = "Cloud Sync"
        )
    }
}
