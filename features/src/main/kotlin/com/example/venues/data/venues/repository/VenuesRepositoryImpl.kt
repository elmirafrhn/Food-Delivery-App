package com.example.venues.data.venues.repository

import com.example.venues.data.location.mapper.toLocationItem
import com.example.venues.data.venues.datasource.local.VenuesLocalDataSource
import com.example.venues.data.venues.datasource.local.entity.VenueEntity
import com.example.venues.data.venues.datasource.remote.VenuesRemoteDataSource
import com.example.venues.data.venues.mappers.toEntity
import com.example.venues.data.venues.mappers.toFavoriteEntity
import com.example.venues.data.venues.mappers.toFavoriteModel
import com.example.venues.data.venues.mappers.toVenueModel
import com.example.venues.domain.interfaces.VenuesRepository
import com.example.venues.domain.model.FavoriteModel
import com.example.venues.domain.model.LocationModel
import com.example.venues.domain.model.VenueModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VenuesRepositoryImpl @Inject constructor(
    private val remoteDataSource: VenuesRemoteDataSource,
    private val localDataSource: VenuesLocalDataSource,
) : VenuesRepository {

    override fun getAllVenuesFavorites(): Flow<List<FavoriteModel>> {
        return localDataSource.getAllVenuesFavorites().map { it.map { it.toFavoriteModel() } }
    }

    override suspend fun insertVenueFavorite(favoriteModel: FavoriteModel) {
        localDataSource.insertVenueFavorite(favoriteModel.toFavoriteEntity())
    }

    override fun getVenues(): Flow<List<VenueModel>> =
        localDataSource.getVenues().map { it.map { it.toVenueModel() } }

    override suspend fun getVenuesByLocation(locationModel: LocationModel) {
        val venues = fetchVenues(locationModel)
        replaceAllVenues(venues)
    }

    private suspend fun fetchVenues(locationModel: LocationModel): List<VenueEntity> {
        return remoteDataSource.getVenues(locationModel.toLocationItem())
            .mapNotNull { it.toEntity() }
    }

    private suspend fun replaceAllVenues(venuesList: List<VenueEntity>) {
        localDataSource.replaceAllVenues(venuesList)
    }
}
