package com.example.venues.data.venues.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val venueId: String,
    val isFavorite: Boolean
)