package com.example.radioplayer.infra

import com.example.radioplayer.domain.RadioStationsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/** Radio service interface with the main api requests*/
interface RadioStationService {
    @GET("v2/stations/{rpuids}")
    suspend fun getRadioByRpuid(
        @Path("rpuids") rpuids: List<String>,
        @Query("include") include: String? = null
    ): Response<RadioStationsResponse>

    @GET("v2/stations")
    suspend fun getStationsByCountryCode(
        @Header("Authorization") authHeader: String,
        @Query("country") country: Int,
        @Query("include") include: String? = null
    ): Response<RadioStationsResponse>

    @GET("v2/stations")
    suspend fun getAllStations(
        @Header("Authorization") authHeader: String,
    ): Response<RadioStationsResponse>
}