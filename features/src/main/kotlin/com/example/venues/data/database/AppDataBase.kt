package com.example.venues.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.venues.data.venues.datasource.local.dao.FavoriteDao
import com.example.venues.data.venues.datasource.local.dao.VenueDao
import com.example.venues.data.venues.datasource.local.entity.FavoriteEntity
import com.example.venues.data.venues.datasource.local.entity.VenueEntity

@Database(entities = [VenueEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
abstract class WoltDatabase : RoomDatabase() {

    abstract fun venueDao(): VenueDao
    abstract fun favoriteDao(): FavoriteDao
}
