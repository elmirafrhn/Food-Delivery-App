package com.example.venues.domain.usecases

import com.example.venues.domain.model.FavoriteModel

interface UpdateFavoriteUseCase {
    suspend operator fun invoke(favoriteModel: FavoriteModel)
}