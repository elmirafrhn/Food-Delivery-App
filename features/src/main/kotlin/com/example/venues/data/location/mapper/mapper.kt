package com.example.venues.data.location.mapper

import com.example.venues.data.location.datasource.model.LocationItem
import com.example.venues.domain.model.LocationModel

fun LocationModel.toLocationItem() = LocationItem(
    lat = lat,
    long = long
)

fun LocationItem.toLocationModel() = LocationModel(
    lat = lat,
    long = long
)