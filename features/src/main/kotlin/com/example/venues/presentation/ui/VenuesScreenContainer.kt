package com.example.venues.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.venues.domain.model.FavoriteModel
import com.example.venues.presentation.VenuesState
import com.example.venues.presentation.VenuesViewModel

// test tags
const val SHIMMER_LOADING = "shimmer_loading"
const val ERROR_SCREEN = "error_screen"
const val VENUES_LIST = "venues_list"

@Composable
fun VenuesScreenContainer(
    viewModel: VenuesViewModel = hiltViewModel()
) {
    val venuesState by viewModel.state.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    /**
     * The observer tied to the current lifecycleOwner.
     * If the lifecycleOwner changes, for example composable moves to another lifecycle.
     * Dispose the old observer and Re-add a new observer for the new lifecycleOwner
     **/

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> viewModel.startSyncing()
                Lifecycle.Event.ON_STOP -> viewModel.stopSyncing()
                else -> Unit
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    VenuesScreen(
        venuesState,
    ) { favoriteModel ->
        viewModel.onFavoriteClicked(favoriteModel)
    }
}

@Composable
fun VenuesScreen(
    venuesState: VenuesState,
    onFavoriteClicked: (FavoriteModel) -> Unit
) {
    when (val state = venuesState) {
        VenuesState.Loading -> {
            ShimmerLoadingScreen(modifier = Modifier.testTag(SHIMMER_LOADING))
        }

        is VenuesState.Error -> {
            ErrorComposable(
                modifier = Modifier.testTag(ERROR_SCREEN),
                state.messageId
            )
        }

        is VenuesState.Success -> {
            VenueList(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(VENUES_LIST),
                state.venues
            ) { favoriteItem ->
                onFavoriteClicked(favoriteItem)
            }
        }
    }
}