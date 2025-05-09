package com.example.venues.data.venues.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class SectionItemDto(
    val items: List<ItemDto>,
    val name: String
)