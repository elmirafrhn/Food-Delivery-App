package com.example.venues.domain.usecases

import com.example.venues.domain.interfaces.VenuesRepository
import com.example.venues.domain.model.VenueModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class GetVenuesUseCaseImpl @Inject constructor(
    private val venuesRepository: VenuesRepository,
) : GetVenuesUseCase {

    override fun invoke(): Flow<List<VenueModel>> =
        venuesRepository.getVenues()
            .combine(venuesRepository.getAllVenuesFavorites())
            { venues, favorites ->
                // TODO: remove all prints and todos
                println("elmira collecting in invoke()")
                venues.map { venue ->
                    venue.copy(
                        isFavorite = favorites.find { it.venueId == venue.id }?.isFavorite == true
                    )
                }
            }.filter { it.isNotEmpty() }
}