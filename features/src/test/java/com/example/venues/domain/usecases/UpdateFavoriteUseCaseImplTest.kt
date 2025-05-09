package com.example.venues.domain.usecases

import com.example.venues.domain.interfaces.VenuesRepository
import com.example.venues.utils.fakeFavoriteModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateFavoriteUseCaseImplTest {

    private lateinit var useCase: UpdateFavoriteUseCase
    private val venuesRepository: VenuesRepository = mock()

    @Before
    fun setup() {
        useCase = UpdateFavoriteUseCaseImpl(venuesRepository)
    }

    @Test
    fun `invoke should call insertVenueFavorite with correct favoriteModel`() = runTest {
        useCase.invoke(fakeFavoriteModel)

        verify(venuesRepository).insertVenueFavorite(fakeFavoriteModel)
        verifyNoMoreInteractions(venuesRepository)
    }
}