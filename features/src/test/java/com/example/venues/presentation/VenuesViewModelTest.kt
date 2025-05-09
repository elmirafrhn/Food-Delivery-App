package com.example.venues.presentation

import com.example.core.errorhandler.NetworkError
import com.example.venues.domain.usecases.GetVenuesUseCase
import com.example.venues.domain.usecases.LocationUseCase
import com.example.venues.domain.usecases.UpdateFavoriteUseCase
import com.example.venues.utils.error.createHttpException
import com.example.venues.utils.fakeVenueModelList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class VenuesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getVenuesUseCase: GetVenuesUseCase = mock()
    private val updateFavoriteUseCase: UpdateFavoriteUseCase = mock()
    private val locationUseCase: LocationUseCase = mock()

    private lateinit var viewModel: VenuesViewModel

    fun initializeViewModel() {
        viewModel = VenuesViewModel(getVenuesUseCase, updateFavoriteUseCase, locationUseCase)
    }

    @Test
    fun `when viewModel initialized state should be Loading`() = runTest {
        whenever(getVenuesUseCase.invoke()).thenReturn(flowOf(fakeVenueModelList))
        whenever(locationUseCase.syncVenuesWithLocation()).thenReturn(Unit)

        initializeViewModel()
        val expected = VenuesState.Loading
        assertEquals(expected, viewModel.state.value)
    }

    @Test
    fun `when venues are fetched successfully then state should be Success`() = runTest {

        whenever(getVenuesUseCase.invoke()).thenReturn(flowOf(fakeVenueModelList))
        whenever(locationUseCase.syncVenuesWithLocation()).thenReturn(Unit)

        initializeViewModel()
        val job = launch {
            viewModel.state.collect { }
        }
        advanceUntilIdle()

        val expected = VenuesState.Success(fakeVenueModelList)
        assertEquals(expected, viewModel.state.value)
        job.cancel()
    }

    @Test
    fun `when fetching venues fails then state should be Error with correct messageId`() = runTest {
        val exception = createHttpException(404, "Not Found")
        whenever(locationUseCase.syncVenuesWithLocation()).thenReturn(Unit)
        whenever(getVenuesUseCase.invoke()).thenReturn(flow { throw exception })

        initializeViewModel()
        val job = launch {
            viewModel.state.collect { }
        }
        advanceUntilIdle()

        assert(viewModel.state.value is VenuesState.Error)
        assert((viewModel.state.value as VenuesState.Error).messageId == NetworkError.NotFound.messageId)
        job.cancel()
    }
}