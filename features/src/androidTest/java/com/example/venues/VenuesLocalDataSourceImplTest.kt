package com.example.venues

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.example.venues.base.WOLT_TEST_DB
import com.example.venues.data.database.WoltDatabase
import com.example.venues.data.venues.datasource.local.dao.FavoriteDao
import com.example.venues.data.venues.datasource.local.dao.VenueDao
import com.example.venues.utils.fakeFavoriteEntity
import com.example.venues.utils.fakeVenuesList
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class VenuesLocalDataSourceImplTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named(WOLT_TEST_DB)
    lateinit var testDatabase: WoltDatabase

    private lateinit var venueDao: VenueDao
    private lateinit var favoriteDao: FavoriteDao

    @Before
    fun setup() {
        hiltRule.inject()
        venueDao = testDatabase.venueDao()
        favoriteDao = testDatabase.favoriteDao()
    }

    @After
    fun closeDb() {
        testDatabase.close()
    }

    @Test
    fun insertListOfVenues() = runTest {
        venueDao.getAllVenues().test {
            assert(awaitItem().isEmpty())
            venueDao.insertVenue(fakeVenuesList)
            assertEquals(fakeVenuesList, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun deleteVenues() = runTest {
        venueDao.insertVenue(fakeVenuesList)
        venueDao.getAllVenues().test {
            assert(awaitItem().isNotEmpty())
            venueDao.deleteAllVenues()
            assert(awaitItem().isEmpty())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun insertVenueFavorite() = runTest {
        favoriteDao.getAllVenuesFavorites().test {
            assert(awaitItem().isEmpty())
            favoriteDao.insertVenueFavorite(fakeFavoriteEntity)
            assertEquals(listOf(fakeFavoriteEntity), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}