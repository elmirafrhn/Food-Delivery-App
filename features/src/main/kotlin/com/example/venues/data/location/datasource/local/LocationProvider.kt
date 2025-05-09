package com.example.venues.data.location.datasource.local

import com.example.venues.data.location.datasource.model.LocationItem
import kotlinx.coroutines.flow.Flow

interface LocationProvider {
    fun provideLocation(): Flow<LocationItem>
}