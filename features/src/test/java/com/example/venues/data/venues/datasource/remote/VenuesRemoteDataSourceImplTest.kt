package com.example.venues.data.venues.datasource.remote

import com.example.venues.data.venues.datasource.remote.api.VenuesApi
import com.example.venues.utils.fakeItemDtoList
import com.example.venues.utils.fakeLocationItem
import com.example.venues.utils.fakeVenuesApiResponse
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class VenuesRemoteDataSourceImplTest {
    private val venueApi: VenuesApi = mock()

    private lateinit var remoteDatasource: VenuesRemoteDataSource

    @Before
    fun setup() {
        remoteDatasource = VenuesRemoteDataSourceImpl(venueApi)
    }

    @Test
    fun `getVenues should correctly map API response to ItemDto lis`() = runTest {
        whenever(venueApi.getVenues(any(), any())).thenReturn(fakeVenuesApiResponse)
        val actual = remoteDatasource.getVenues(fakeLocationItem)
        val expected = fakeItemDtoList
        assertEquals(expected, actual)
    }
}