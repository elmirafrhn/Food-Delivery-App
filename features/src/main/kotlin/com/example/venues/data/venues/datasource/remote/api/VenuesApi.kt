package com.example.venues.data.venues.datasource.remote.api

import com.example.venues.data.venues.datasource.remote.model.VenuesApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VenuesApi {
    @GET("pages/restaurants")
    suspend fun getVenues(
        @Query("lat") lat: String,
        @Query("lon") long: String
    ): VenuesApiResponse
}