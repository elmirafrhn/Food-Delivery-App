package com.example.venues.data.venues.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.venues.data.venues.datasource.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenueFavorite(entity: FavoriteEntity)

    @Query("SELECT * FROM favorites")
    fun getAllVenuesFavorites(): Flow<List<FavoriteEntity>>
}