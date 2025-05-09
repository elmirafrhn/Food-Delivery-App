package com.example.venues.data.location.repository

import com.example.venues.data.location.datasource.local.LocationProvider
import com.example.venues.data.location.mapper.toLocationModel
import com.example.venues.domain.interfaces.LocationRepository
import com.example.venues.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationProvider: LocationProvider
) : LocationRepository {
    override fun provideLocation(): Flow<LocationModel> =
        locationProvider.provideLocation().map { it.toLocationModel() }
}