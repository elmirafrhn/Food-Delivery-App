package com.example.venues.domain.usecases

import com.example.venues.domain.model.VenueModel
import kotlinx.coroutines.flow.Flow

interface GetVenuesUseCase {
    operator fun invoke(): Flow<List<VenueModel>>
}