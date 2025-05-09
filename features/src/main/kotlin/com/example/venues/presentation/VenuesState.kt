package com.example.venues.presentation

import androidx.annotation.StringRes
import com.example.venues.domain.model.VenueModel

sealed interface VenuesState {
    data object Loading : VenuesState
    data class Success(val venues: List<VenueModel>) : VenuesState
    data class Error(@StringRes val messageId: Int) : VenuesState
}