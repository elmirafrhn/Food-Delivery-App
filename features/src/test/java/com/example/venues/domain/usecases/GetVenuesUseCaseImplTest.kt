package com.example.venues.domain.usecases

import com.example.venues.domain.interfaces.VenuesRepository
import com.example.venues.utils.fakeFavoriteModelList
import com.example.venues.utils.fakeVenueModelList
import com.example.venues.utils.fakeVenuesModelListWithNewFavoritesValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class GetVenuesUseCaseImplTest {

    private val venuesRepository: VenuesRepository = mock()

    private lateinit var useCase: GetVenuesUseCase

    @Before
    fun setup() {
        useCase = GetVenuesUseCaseImpl(venuesRepository)
    }

    @Test
    fun `should combine venues and favorites to produce correctly mapped venues`() = runTest {

        whenever(venuesRepository.getVenues()).thenReturn(flowOf(fakeVenueModelList))
        whenever(venuesRepository.getAllVenuesFavorites()).thenReturn(flowOf(fakeFavoriteModelList))

        val result = useCase.invoke().first()

        val expected = fakeVenuesModelListWithNewFavoritesValue
        assertEquals(expected, result)

        verify(venuesRepository).getVenues()
        verify(venuesRepository).getAllVenuesFavorites()
        verifyNoMoreInteractions(venuesRepository)
    }
}