package com.example.myapplicationindividual.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StarWarsCacheDao {

    @Query("SELECT * FROM starwars_cache ORDER BY CAST(id AS INTEGER) ASC")
    fun observeAll(): Flow<List<StarWarsCacheEntity>>

    @Query("SELECT COUNT(*) FROM starwars_cache")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<StarWarsCacheEntity>)
}
