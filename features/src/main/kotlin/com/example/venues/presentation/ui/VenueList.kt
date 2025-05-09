package com.example.venues.presentation.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.venues.domain.model.FavoriteModel
import com.example.venues.domain.model.VenueModel

const val ITEM_COMPOSABLE_TEST_TAG = "venue_item_"

@Composable
fun VenueList(
    modifier: Modifier,
    venues: List<VenueModel>,
    onFavoriteClicked: (FavoriteModel) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(venues, key = { it.id }) { item ->
            ItemComposable(
                modifier = Modifier
                    .testTag("$ITEM_COMPOSABLE_TEST_TAG${item.id}")
                    .animateItem(
                        placementSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow // how quickly item snaps back
                        )
                    ),
                venue = item,
                onFavoriteClicked = { favoriteItem ->
                    onFavoriteClicked(favoriteItem)
                })
        }
    }
}
