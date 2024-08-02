//package com.example.radioplayer.infra
//
//import com.example.radioplayer.domain.RadioStationsResponse
//import retrofit2.Response
//import retrofit2.http.GET
//import retrofit2.http.Path
//import retrofit2.http.Query
//
///** Radio service interface with the main api requests*/
//interface RadioStationService {
//    @GET("v2/stations/{rpuids}")
//    suspend fun getRadioByRpuid(
//        @Path("rpuids") rpuids: String
//    ): Response<RadioStationsResponse>
//
//    @GET("v2/stations")
//    suspend fun getStationsByCountryCode(
//        @Query("country") country: Int,
//    ): Response<RadioStationsResponse>
//
//    @GET("v2/stations")
//    suspend fun getAllStations(
//    ): Response<RadioStationsResponse>
//}