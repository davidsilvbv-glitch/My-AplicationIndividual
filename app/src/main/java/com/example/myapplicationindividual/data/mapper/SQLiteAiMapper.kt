package com.example.myapplicationindividual.data.mapper

import com.example.myapplicationindividual.data.dto.SQLiteAiRowDto
import com.example.myapplicationindividual.domain.model.CloudDatabaseItem

object SQLiteAiMapper {
    fun toDomain(dto: SQLiteAiRowDto): CloudDatabaseItem {
        val albumIdStr = dto.albumId?.toString() ?: dto.id ?: ""
        val albumTitle = dto.title ?: "Álbum sin título"
        val artistInfo = if (dto.artistId != null) "Artista ID: ${dto.artistId}" else dto.description ?: "Base de datos SQLite Cloud"

        return CloudDatabaseItem(
            id = albumIdStr,
            title = albumTitle,
            description = artistInfo,
            createdAt = dto.createdAt ?: "Chinook DB (SQLite Cloud)"
        )
    }
}
