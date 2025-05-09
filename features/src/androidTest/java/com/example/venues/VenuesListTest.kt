package com.example.venues

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.venues.domain.model.VenueModel
import com.example.venues.presentation.ui.ITEM_COMPOSABLE_TEST_TAG
import com.example.venues.presentation.ui.ItemComposable
import com.example.venues.presentation.ui.VenueList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class VenuesListTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeVenueList = listOf(
        VenueModel(
            id = "1",
            name = "Venue One",
            description = "description",
            imageUrl = "",
            isFavorite = false
        ),
        VenueModel(
            id = "2",
            name = "Venue Two",
            description = "description",
            imageUrl = "",
            isFavorite = true
        )
    )

    @Test
    fun venueListIsDisplayedItemsDataCorrectly() {
        composeTestRule.setContent {
            VenueList(modifier = Modifier, fakeVenueList) { }
        }

        fakeVenueList.forEach {
            composeTestRule
                .onNodeWithTag("$ITEM_COMPOSABLE_TEST_TAG${it.id}")
                .assertIsDisplayed()
        }
    }

    @Test
    fun itemComposable_displaysVenueNameAndDescription() {
        val venue = VenueModel("1", "Test Venue", "A cool place", "url", false)

        composeTestRule.setContent {
            ItemComposable(
                modifier = Modifier,
                venue = venue,
                onFavoriteClicked = {}
            )
        }

        composeTestRule.onNodeWithText("Test Venue").assertIsDisplayed()
        composeTestRule.onNodeWithText("A cool place").assertIsDisplayed()
    }
}
