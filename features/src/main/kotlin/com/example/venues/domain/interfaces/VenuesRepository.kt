package com.example.venues.domain.interfaces

import com.example.venues.domain.model.FavoriteModel
import com.example.venues.domain.model.LocationModel
import com.example.venues.domain.model.VenueModel
import kotlinx.coroutines.flow.Flow

interface VenuesRepository {
    fun getVenues(): Flow<List<VenueModel>>
    fun getAllVenuesFavorites(): Flow<List<FavoriteModel>>
    suspend fun getVenuesByLocation(locationModel: LocationModel)
    suspend fun insertVenueFavorite(favoriteEntity: FavoriteModel)
}