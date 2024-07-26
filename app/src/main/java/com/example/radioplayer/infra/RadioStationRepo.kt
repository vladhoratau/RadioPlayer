package com.example.radioplayer.infra

import com.example.radioplayer.domain.RadioStationsResponse
import retrofit2.Response
import javax.inject.Inject

/** Radio station repo interface with methods for specific operations */
interface RadioStationRepo {
    suspend fun getStationsByCountryCode(
        country: Int
    ): Response<RadioStationsResponse>

    suspend fun getStationByRPUID(
        rpuid: String
    ): Response<RadioStationsResponse>
}

/**Implementation of Radio station repo class */
class RadioStationsRepoImpl @Inject constructor(private val radioStationService: RadioStationService) :
    RadioStationRepo {
    override suspend fun getStationsByCountryCode(
        country: Int
    ): Response<RadioStationsResponse> {
        return radioStationService.getStationsByCountryCode(country)
    }

    override suspend fun getStationByRPUID(
        rpuid: String
    ): Response<RadioStationsResponse> {
        return radioStationService.getRadioByRpuid(rpuid)
    }
}
