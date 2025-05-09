package com.example.venues.domain.interfaces

import com.example.venues.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun provideLocation(): Flow<LocationModel>
}