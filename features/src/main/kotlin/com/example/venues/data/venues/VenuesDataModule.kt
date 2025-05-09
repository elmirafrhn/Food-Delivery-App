package com.example.venues.data.venues

import com.example.venues.data.venues.datasource.local.VenuesLocalDataSource
import com.example.venues.data.venues.datasource.local.VenuesLocalDataSourceImpl
import com.example.venues.data.venues.datasource.remote.VenuesRemoteDataSource
import com.example.venues.data.venues.datasource.remote.VenuesRemoteDataSourceImpl
import com.example.venues.data.venues.datasource.remote.api.VenuesApi
import com.example.venues.data.venues.repository.VenuesRepositoryImpl
import com.example.venues.domain.interfaces.VenuesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface VenuesDataModule {
    @Binds
    fun bindVenuesLocalDataSource(dataSource: VenuesLocalDataSourceImpl): VenuesLocalDataSource

    @Binds
    fun bindVenuesRemoteDataSource(dataSourceImpl: VenuesRemoteDataSourceImpl): VenuesRemoteDataSource

    @Binds
    fun bindVenuesRepository(repository: VenuesRepositoryImpl): VenuesRepository

    companion object {
        @Provides
        fun provideCatalogAPI(retrofit: Retrofit): VenuesApi = retrofit.create()
    }
}