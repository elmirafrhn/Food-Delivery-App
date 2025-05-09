package com.example.venues.data.database

import android.content.Context
import androidx.room.Room
import com.example.venues.data.venues.datasource.local.dao.FavoriteDao
import com.example.venues.data.venues.datasource.local.dao.VenueDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val WOLT_DB = "wolt_db"

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): WoltDatabase {
        return Room.databaseBuilder(
            context,
            WoltDatabase::class.java,
            WOLT_DB
        ).build()
    }

    @Provides
    @Singleton
    fun provideVenueDao(appDatabase: WoltDatabase): VenueDao {
        return appDatabase.venueDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(appDatabase: WoltDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }
}