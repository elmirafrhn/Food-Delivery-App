package com.example.venues.domain.usecases

interface LocationUseCase {
    suspend fun syncVenuesWithLocation()
}