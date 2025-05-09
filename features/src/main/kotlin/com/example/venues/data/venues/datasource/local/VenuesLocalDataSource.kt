package com.example.venues.data.venues.datasource.local

import com.example.venues.data.venues.datasource.local.entity.FavoriteEntity
import com.example.venues.data.venues.datasource.local.entity.VenueEntity
import kotlinx.coroutines.flow.Flow

interface VenuesLocalDataSource {
    suspend fun replaceAllVenues(venues: List<VenueEntity>)
    fun getVenues(): Flow<List<VenueEntity>>
    suspend fun insertVenueFavorite(favoriteEntity: FavoriteEntity)
    fun getAllVenuesFavorites(): Flow<List<FavoriteEntity>>
}