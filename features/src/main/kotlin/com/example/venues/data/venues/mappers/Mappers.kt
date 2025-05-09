package com.example.venues.data.venues.mappers

import com.example.venues.data.venues.datasource.local.entity.FavoriteEntity
import com.example.venues.data.venues.datasource.local.entity.VenueEntity
import com.example.venues.data.venues.datasource.remote.model.ItemDto
import com.example.venues.data.venues.datasource.remote.model.VenuesApiResponse
import com.example.venues.domain.model.FavoriteModel
import com.example.venues.domain.model.VenueModel

const val VENUE_SECTION_NAME = "restaurants-delivering-venues"
fun ItemDto.toEntity(): VenueEntity? {
    return venue?.let {
        VenueEntity(
            id = it.id,
            name = it.name,
            description = it.shortDescription,
            imageUrl = image.url
        )
    }
}

fun VenueEntity.toVenueModel() = VenueModel(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun FavoriteModel.toFavoriteEntity() = FavoriteEntity(
    venueId = venueId,
    isFavorite = isFavorite
)

fun FavoriteEntity.toFavoriteModel() = FavoriteModel(
    venueId = venueId,
    isFavorite = isFavorite
)

fun VenuesApiResponse.extractVenuesList(): List<ItemDto> =
    this.sections.firstOrNull { it.name == VENUE_SECTION_NAME }?.items.orEmpty()