package com.example.venues.utils

import com.example.venues.data.venues.datasource.local.entity.FavoriteEntity
import com.example.venues.data.venues.datasource.local.entity.VenueEntity

val fakeVenuesList = listOf(
    VenueEntity(
        id = "1",
        name = "McDonald's Helsinki Kamppi",
        description = "I'm lovin' it.",
        imageUrl = "https://imageproxy.wolt.com/assets/6735be5986f45b72713e2129"
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

val fakeFavoriteEntity = FavoriteEntity("1", true)