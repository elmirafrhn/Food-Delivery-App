package com.example.venues.data.venues.datasource.remote

import com.example.venues.data.location.datasource.model.LocationItem
import com.example.venues.data.venues.datasource.remote.api.VenuesApi
import com.example.venues.data.venues.datasource.remote.model.ItemDto
import com.example.venues.data.venues.mappers.extractVenuesList
import javax.inject.Inject

class VenuesRemoteDataSourceImpl @Inject constructor(
    private val venueApi: VenuesApi
) : VenuesRemoteDataSource {
    override suspend fun getVenues(location: LocationItem): List<ItemDto> =
        venueApi.getVenues(location.lat, location.long).extractVenuesList().take(15)
}