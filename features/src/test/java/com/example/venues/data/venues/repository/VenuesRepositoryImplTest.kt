package com.example.venues.data.venues.repository

import app.cash.turbine.test
import com.example.venues.data.venues.datasource.local.VenuesLocalDataSource
import com.example.venues.data.venues.datasource.remote.VenuesRemoteDataSource
import com.example.venues.domain.interfaces.VenuesRepository
import com.example.venues.utils.fakeFavoriteEntityList
import com.example.venues.utils.fakeFavoriteModelList
import com.example.venues.utils.fakeListOfItemDto
import com.example.venues.utils.fakeLocationItem
import com.example.venues.utils.fakeLocationModel
import com.example.venues.utils.fakeVenueModelList
import com.example.venues.utils.fakeVenuesEntityList
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class VenuesRepositoryImplTest {
    private val remoteDataSource: VenuesRemoteDataSource = mock()
    private val localDataSource: VenuesLocalDataSource = mock()

    private lateinit var venuesRepository: VenuesRepository

    @Before
    fun setup() {
        venuesRepository = VenuesRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `getVenues should map result and return list of VenueModel`() = runTest {
        whenever(localDataSource.getVenues()).thenReturn(flowOf(fakeVenuesEntityList))
        val expected = fakeVenueModelList

        venuesRepository.getVenues().test {
            assertEquals(expected, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `venues should be fetched and updated based on location`() =
        runTest {
            whenever(remoteDataSource.getVenues(fakeLocationItem)).thenReturn(fakeListOfItemDto)
            venuesRepository.getVenuesByLocation(fakeLocationModel)

            verify(remoteDataSource).getVenues(fakeLocationItem)
            verify(localDataSource).replaceAllVenues(fakeVenuesEntityList)
        }

    @Test
    fun `getAllVenuesFavorite should map result and return a list of FavoriteModel`() = runTest {
        whenever(localDataSource.getAllVenuesFavorites()).thenReturn(flowOf(fakeFavoriteEntityList))

        val expected = fakeFavoriteModelList

        venuesRepository.getAllVenuesFavorites().test {
            assertEquals(expected, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}