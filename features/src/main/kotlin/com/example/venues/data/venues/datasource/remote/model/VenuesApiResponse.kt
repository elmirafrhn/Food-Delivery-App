package com.example.venues.data.venues.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class VenuesApiResponse(
    val sections: List<SectionItemDto>
)