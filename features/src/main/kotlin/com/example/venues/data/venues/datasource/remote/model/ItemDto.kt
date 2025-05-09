package com.example.venues.data.venues.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ItemDto(
    val venue: VenueDto? = null,
    val image: VenueImageDto
)