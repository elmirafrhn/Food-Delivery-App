package com.example.venues.domain.usecases

import com.example.venues.domain.interfaces.LocationRepository
import com.example.venues.domain.interfaces.VenuesRepository
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class LocationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository,
    private val venuesRepository: VenuesRepository
) : LocationUseCase {

    override suspend fun syncVenuesWithLocation() {
        locationRepository.provideLocation().collectLatest { location ->
            println("elmira collecting in syncVenuesWithLocation()")
            venuesRepository.getVenuesByLocation(location)
        }
    }
}