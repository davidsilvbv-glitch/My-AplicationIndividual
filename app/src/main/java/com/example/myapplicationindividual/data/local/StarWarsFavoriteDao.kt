package com.example.myapplicationindividual.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StarWarsFavoriteDao {

    @Query("SELECT * FROM starwars_favorites ORDER BY addedAt DESC")
    fun observeFavorites(): Flow<List<StarWarsFavoriteEntity>>

    @Query("SELECT id FROM starwars_favorites")
    fun observeFavoriteIds(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: StarWarsFavoriteEntity)

    @Query("DELETE FROM starwars_favorites WHERE id = :id")
    suspend fun deleteById(id: String)
}
