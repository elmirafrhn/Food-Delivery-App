package com.example.venues.domain.usecases

import com.example.venues.domain.interfaces.LocationRepository
import com.example.venues.domain.interfaces.VenuesRepository
import com.example.venues.utils.fakeLocationModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class LocationUseCaseImplTest {

    private val locationRepository: LocationRepository = mock()
    private val venuesRepository: VenuesRepository = mock()

    private lateinit var useCase: LocationUseCase

    @Before
    fun setup() {
        useCase = LocationUseCaseImpl(locationRepository, venuesRepository)
    }

    @Test
    fun `should fetch venues for each emitted location from repository`() = runTest {
        whenever(locationRepository.provideLocation()).thenReturn(flowOf(fakeLocationModel))

        useCase.syncVenuesWithLocation()

        verify(locationRepository, times(1)).provideLocation()
        verify(venuesRepository, times(1)).getVenuesByLocation(fakeLocationModel)
        verifyNoMoreInteractions(locationRepository, venuesRepository)
    }
}