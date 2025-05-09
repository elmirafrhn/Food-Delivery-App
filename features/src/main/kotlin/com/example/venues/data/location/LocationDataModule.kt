package com.example.venues.data.location

import com.example.core.coroutinesModule.DispatcherProvider
import com.example.venues.data.location.datasource.local.LocationProvider
import com.example.venues.data.location.datasource.local.LocationProviderImpl
import com.example.venues.data.location.repository.LocationRepositoryImpl
import com.example.venues.domain.interfaces.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
interface LocationDataModule {
    @Binds
    fun bindLocationRepository(repository: LocationRepositoryImpl): LocationRepository

    companion object {
        const val DELAY_DURATION = "delay"

        @Provides
        @Named(DELAY_DURATION)
        fun provideDelayDuration(): Long = 10_000

        @Provides
        @ViewModelScoped
        fun provideLocationDataSource(
            dispatcherProvider: DispatcherProvider,
            @Named(DELAY_DURATION) delay: Long
        ): LocationProvider =
            LocationProviderImpl(dispatcherProvider, delay)
    }
}