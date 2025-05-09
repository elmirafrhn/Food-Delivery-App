package com.example.venues.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.errorhandler.ErrorHandler
import com.example.venues.domain.model.FavoriteModel
import com.example.venues.domain.usecases.GetVenuesUseCase
import com.example.venues.domain.usecases.LocationUseCase
import com.example.venues.domain.usecases.UpdateFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VenuesViewModel @Inject constructor(
    private val getVenuesUseCase: GetVenuesUseCase,
    private val favoriteUseCase: UpdateFavoriteUseCase,
    private val locationUseCase: LocationUseCase
) : ViewModel() {

    // Job tracking location sync coroutine so we can cancel it later
    private var _syncLocationJob: Job? = null

    private val _state = MutableStateFlow<VenuesState>(VenuesState.Loading)
    val state: StateFlow<VenuesState>
        get() = _state

    init {
        getVenues()
    }

    private fun getVenues() {
        viewModelScope.launch {
            runCatching {
                getVenuesUseCase.invoke().collect { venues ->
                    _state.update { VenuesState.Success(venues) }
                }
            }.onFailure { throwable ->
                ErrorHandler.errorMessage(throwable).let { message ->
                    _state.update { VenuesState.Error(message.messageId) }
                }
            }
        }
    }

    private fun syncVenuesWithLocation() {
        if (_syncLocationJob?.isActive != true) {
            _syncLocationJob = viewModelScope.launch {
                runCatching {
                    locationUseCase.syncVenuesWithLocation()
                }.onFailure { throwable ->
                    if (throwable !is CancellationException)
                        ErrorHandler.errorMessage(throwable).let { message ->
                            _state.update { VenuesState.Error(message.messageId) }
                        }
                }
            }
        }
    }

    fun onFavoriteClicked(favoriteModel: FavoriteModel) {
        viewModelScope.launch { favoriteUseCase.invoke(favoriteModel) }
    }

    // start syncing location updates, called from a LifecycleEventObserver onStart
    fun startSyncing() {
        syncVenuesWithLocation()
    }

    // stop syncing location updates,called from a LifecycleEventObserver onStop
    fun stopSyncing() {
        _syncLocationJob?.cancel()
        _syncLocationJob = null
    }
}