package com.example.venues.data.venues.datasource.remote

import com.example.venues.data.location.datasource.model.LocationItem
import com.example.venues.data.venues.datasource.remote.model.ItemDto

interface VenuesRemoteDataSource {
    suspend fun getVenues(location: LocationItem): List<ItemDto>
}