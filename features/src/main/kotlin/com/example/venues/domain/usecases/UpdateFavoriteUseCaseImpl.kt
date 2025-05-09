package com.example.venues.domain.usecases

import com.example.venues.domain.interfaces.VenuesRepository
import com.example.venues.domain.model.FavoriteModel
import javax.inject.Inject

class UpdateFavoriteUseCaseImpl @Inject constructor(
    private val venuesRepository: VenuesRepository,
) : UpdateFavoriteUseCase {
    override suspend fun invoke(favoriteModel: FavoriteModel) {
        venuesRepository.insertVenueFavorite(favoriteModel)
    }
}