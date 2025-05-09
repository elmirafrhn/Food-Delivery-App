package com.example.venues.domain.model

data class VenueModel(
    val id: String,
    val name: String,
    val description: String?,
    val imageUrl: String,
    val isFavorite: Boolean = false,
)