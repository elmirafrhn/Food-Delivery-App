package com.example.venues.domain

import com.example.venues.domain.usecases.GetVenuesUseCase
import com.example.venues.domain.usecases.GetVenuesUseCaseImpl
import com.example.venues.domain.usecases.LocationUseCase
import com.example.venues.domain.usecases.LocationUseCaseImpl
import com.example.venues.domain.usecases.UpdateFavoriteUseCase
import com.example.venues.domain.usecases.UpdateFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    @ViewModelScoped
    fun bindGetVenuesUseCase(useCase: GetVenuesUseCaseImpl): GetVenuesUseCase

    @Binds
    @ViewModelScoped
    fun bindUpdateFavoriteUseCase(useCase: UpdateFavoriteUseCaseImpl): UpdateFavoriteUseCase

    @Binds
    @ViewModelScoped
    fun bindGetLocationUseCase(useCase: LocationUseCaseImpl): LocationUseCase
}