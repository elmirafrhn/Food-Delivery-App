package com.example.venues.data.venues.datasource.local

import com.example.venues.data.venues.datasource.local.dao.FavoriteDao
import com.example.venues.data.venues.datasource.local.dao.VenueDao
import com.example.venues.data.venues.datasource.local.entity.FavoriteEntity
import com.example.venues.data.venues.datasource.local.entity.VenueEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VenuesLocalDataSourceImpl @Inject constructor(
    private val venueDao: VenueDao,
    private val favoriteDao: FavoriteDao
) : VenuesLocalDataSource {
    override suspend fun replaceAllVenues(venues: List<VenueEntity>) {
        venueDao.replaceAllVenues(venues)
    }

    override fun getVenues(): Flow<List<VenueEntity>> = venueDao.getAllVenues()

    override suspend fun insertVenueFavorite(favoriteEntity: FavoriteEntity) {
        favoriteDao.insertVenueFavorite(favoriteEntity)
    }

    override fun getAllVenuesFavorites(): Flow<List<FavoriteEntity>> =
        favoriteDao.getAllVenuesFavorites()
}