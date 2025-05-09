package com.example.venues

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.venues.domain.model.VenueModel
import com.example.venues.presentation.VenuesState
import com.example.venues.presentation.ui.ERROR_SCREEN
import com.example.venues.presentation.ui.SHIMMER_LOADING
import com.example.venues.presentation.ui.VENUES_LIST
import com.example.venues.presentation.ui.VenuesScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class StateLessVenuesScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    val fakeVenueModelList = listOf(
        VenueModel(
            id = "1",
            name = "McDonald's Helsinki Kamppi",
            description = "I'm lovin' it.",
            imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129",
            isFavorite = true
        ),
        VenueModel(
            id = "2",
            name = "Bastard Burgers Mikonkatu",
            description = "Like a Bastard",
            imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129",
            isFavorite = false
        ),
        VenueModel(
            id = "3",
            name = "Siipiweikot Kamppi",
            description = "Legendaarisen herkulliset siivet & lisukkeet",
            imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129",
            isFavorite = false
        )
    )

    @Test
    fun displayLoadingState() {
        composeTestRule.setContent {
            VenuesScreen(
                venuesState = VenuesState.Loading,
                retry = {},
                onFavoriteClicked = {}
            )
        }
        composeTestRule
            .onNodeWithTag(SHIMMER_LOADING)
            .assertIsDisplayed()
    }

    @Test
    fun displayErrorState_withCorrectErrorMessage() {
        val errorMessage = "Timeout"
        composeTestRule.setContent {
            VenuesScreen(
                venuesState = VenuesState.Error(R.string.network_error_timeout),
                retry = {},
                onFavoriteClicked = {}
            )
        }
        // error screen displayed
        composeTestRule
            .onNodeWithTag(ERROR_SCREEN)
            .assertIsDisplayed()
        // correct message
        composeTestRule
            .onNodeWithText(errorMessage)
            .assertIsDisplayed()
    }

    @Test
    fun displaySuccessState_WithVenueList() {
        composeTestRule.setContent {
            VenuesScreen(
                venuesState = VenuesState.Success(fakeVenueModelList),
                retry = {},
                onFavoriteClicked = {}
            )
        }
        composeTestRule
            .onNodeWithTag(VENUES_LIST)
            .assertIsDisplayed()
    }
}
