package com.example.venues.utils

import com.example.venues.data.location.datasource.model.LocationItem
import com.example.venues.data.venues.datasource.local.entity.FavoriteEntity
import com.example.venues.data.venues.datasource.local.entity.VenueEntity
import com.example.venues.data.venues.datasource.remote.model.ItemDto
import com.example.venues.data.venues.datasource.remote.model.SectionItemDto
import com.example.venues.data.venues.datasource.remote.model.VenueDto
import com.example.venues.data.venues.datasource.remote.model.VenueImageDto
import com.example.venues.data.venues.datasource.remote.model.VenuesApiResponse
import com.example.venues.data.venues.mappers.VENUE_SECTION_NAME
import com.example.venues.domain.model.FavoriteModel
import com.example.venues.domain.model.LocationModel
import com.example.venues.domain.model.VenueModel

const val VENUES_PAGE_CATEGORIES = "restaurants_page_categories"

val fakeVenueDto = VenueDto("1", "name", "short description")
val fakeVenueImageDto = VenueImageDto("url")
val fakeItemDto = ItemDto(fakeVenueDto, fakeVenueImageDto)
val fakeItemDtoList = listOf(fakeItemDto)
val fakeSectionDto = SectionItemDto(fakeItemDtoList, VENUE_SECTION_NAME)
val fakeSectionDtoContainsWrongName = SectionItemDto(fakeItemDtoList, VENUES_PAGE_CATEGORIES)
val fakeVenuesApiResponse =
    VenuesApiResponse(listOf(fakeSectionDto, fakeSectionDtoContainsWrongName))

val fakeLocationItem = LocationItem("lat", "lomg")
val fakeLocationModel = LocationModel("lat", "lomg")

val fakeListOfItemDto = listOf(
    ItemDto(
        VenueDto(
            id = "1",
            name = "McDonald's Helsinki Kamppi",
            shortDescription = "I'm lovin' it."
        ),
        image = VenueImageDto(url = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129")
    ),
    ItemDto(
        VenueDto(
            id = "2",
            name = "Bastard Burgers Mikonkatu",
            shortDescription = "Like a Bastard"
        ),
        image = VenueImageDto(url = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129")
    ),
    ItemDto(
        VenueDto(
            id = "3",
            name = "Siipiweikot Kamppi",
            shortDescription = "Legendaarisen herkulliset siivet & lisukkeet"
        ),
        image = VenueImageDto(url = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129"),
    )
)
val fakeVenuesEntityList = listOf(
    VenueEntity(
        id = "1",
        name = "McDonald's Helsinki Kamppi",
        description = "I'm lovin' it.",
        imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129",
    ),
    VenueEntity(
        id = "2",
        name = "Bastard Burgers Mikonkatu",
        description = "Like a Bastard",
        imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129"
    ),
    VenueEntity(
        id = "3",
        name = "Siipiweikot Kamppi",
        description = "Legendaarisen herkulliset siivet & lisukkeet",
        imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129"
    )
)

val fakeVenueModelList = listOf(
    VenueModel(
        id = "1",
        name = "McDonald's Helsinki Kamppi",
        description = "I'm lovin' it.",
        imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129",
        isFavorite = false
    ),
    VenueModel(
        id = "2",
        name = "Bastard Burgers Mikonkatu",
        description = "Like a Bastard",
        imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129",
        isFavorite = false
    ),
    VenueModel(
        id = "3",
        name = "Siipiweikot Kamppi",
        description = "Legendaarisen herkulliset siivet & lisukkeet",
        imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129",
        isFavorite = false
    )
)

val fakeFavoriteEntityList = listOf(
    FavoriteEntity("1", true),
    FavoriteEntity("2", true),
    FavoriteEntity("3", true)
)

val fakeFavoriteModel = FavoriteModel("1", true)

val fakeFavoriteModelList = listOf(
    FavoriteModel("1", true),
    FavoriteModel("2", true),
    FavoriteModel("3", true),
)

val fakeVenuesModelListWithNewFavoritesValue = listOf(
    VenueModel(
        id = "1",
        name = "McDonald's Helsinki Kamppi",
        description = "I'm lovin' it.",
        imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129",
        isFavorite = true
    ),
    VenueModel(
        id = "2",
        name = "Bastard Burgers Mikonkatu",
        description = "Like a Bastard",
        imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129",
        isFavorite = true
    ),
    VenueModel(
        id = "3",
        name = "Siipiweikot Kamppi",
        description = "Legendaarisen herkulliset siivet & lisukkeet",
        imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129",
        isFavorite = true
    )
)