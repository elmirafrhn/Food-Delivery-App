package com.example.venues.data.venues.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.venues.data.venues.datasource.local.entity.VenueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VenueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenue(venue: List<VenueEntity>)

    @Query("DELETE FROM venues")
    suspend fun deleteAllVenues()

    @Query("SELECT * FROM venues")
    fun getAllVenues(): Flow<List<VenueEntity>>

    @Transaction
    suspend fun replaceAllVenues(venues: List<VenueEntity>) {
        deleteAllVenues()
        insertVenue(venues)
    }
}
