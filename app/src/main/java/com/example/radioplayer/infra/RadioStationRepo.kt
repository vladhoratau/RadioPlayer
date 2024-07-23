package com.example.radioplayer.infra

import com.example.radioplayer.domain.RadioStationData
import retrofit2.Response
import javax.inject.Inject

/** Radio station repo interface with methods for specific operations */
interface RadioStationRepo {
    suspend fun getStationsByCountryCode(
        authHeader: String,
        stationId:String,
        include:String
    ): Response<List<RadioStationData>>
}

/**Implementation of Radio station repo class */
//class RadioStationsRepoImpl @Inject constructor(private val radioStationService: RadioStationService) : RadioStationRepo {
//    override suspend fun getStationsByCountryCode(
//        authHeader: String,
//        stationId: String,
//        include: String
//    ): Response<List<RadioStationData>> {
//        return radioStationService.getStationsByCountryCode(authHeader, stationId, include)
//    }

//}