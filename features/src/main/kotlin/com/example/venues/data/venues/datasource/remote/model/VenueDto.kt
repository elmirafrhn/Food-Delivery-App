package com.example.venues.data.venues.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VenueDto(
    val id: String,
    val name: String,
    @SerialName("short_description")
    val shortDescription: String? = null

)