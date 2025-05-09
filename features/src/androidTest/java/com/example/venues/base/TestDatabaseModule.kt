package com.example.venues.base

import android.content.Context
import androidx.room.Room
import com.example.venues.data.database.WoltDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

const val WOLT_TEST_DB = "wolt_test_db"

@Module
@InstallIn(SingletonComponent::class)
object TestDatabaseModule {
    @Provides
    @Named(WOLT_TEST_DB)
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, WoltDatabase::class.java
        ).allowMainThreadQueries().build()
}